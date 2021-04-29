package br.com.zup.proposta.cartao.associacateiradigital;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRespository;
import br.com.zup.proposta.cartao.associacateiradigital.carteira.AssociacaoDeCartaoEmCarteiraDigital;
import br.com.zup.proposta.cartao.associacateiradigital.carteira.AssociacaoDeCartaoEmCarteiraDigitalRepository;
import br.com.zup.proposta.cartao.associacateiradigital.requisicao.SolicitaAssociacaoPaypalFeignClient;
import br.com.zup.proposta.cartao.associacateiradigital.requisicao.SolicitaAssociacaoPaypalRequest;

@RestController
@RequestMapping("/cartoes/associacarteiradigital")
public class AssociaPaypalController {

	AssociacaoDeCartaoEmCarteiraDigitalRepository associacaoDeCartaoEmCarteiraDigitalRepository;
	SolicitaAssociacaoPaypalFeignClient associaPaypalFeignClient;
	CartaoRespository cartaoRespository;

	public AssociaPaypalController(
			AssociacaoDeCartaoEmCarteiraDigitalRepository associacaoDeCartaoEmCarteiraDigitalRepository,
			SolicitaAssociacaoPaypalFeignClient associaPaypalFeignClient, CartaoRespository cartaoRespository) {
		this.associacaoDeCartaoEmCarteiraDigitalRepository = associacaoDeCartaoEmCarteiraDigitalRepository;
		this.associaPaypalFeignClient = associaPaypalFeignClient;
		this.cartaoRespository = cartaoRespository;
	}

	@PostMapping("/{id}")
	public ResponseEntity<Object> associaEmCarteiraDigital(@PathVariable(value = "id", required = true) String idCartao,
			@RequestBody @Valid AssociaPaypalRequest associaPaypalRequest,
			@RequestHeader(value = "User-Agent") String userAgent, UriComponentsBuilder uriComponentsBuilder) {

		Optional<Cartao> cartao = cartaoRespository.findById(idCartao);

		if (!cartao.isPresent())
			return ResponseEntity.notFound().build();

		List<AssociacaoDeCartaoEmCarteiraDigital> cartaoAssociado = associacaoDeCartaoEmCarteiraDigitalRepository
				.findByCartao_IdAndCarteira(idCartao, CarteirasDigitais.PAYPAL);
		if (!cartaoAssociado.isEmpty())
			return ResponseEntity.unprocessableEntity().build();

		try {
			SolicitaAssociacaoPaypalRequest solicitaAssociacaoPaypalRequest = associaPaypalRequest
					.converterParaSolicitaAssociacaoPaypalRequest();
			associaPaypalFeignClient.associaPaypal(solicitaAssociacaoPaypalRequest, idCartao);

			AssociacaoDeCartaoEmCarteiraDigital associacaoDeCartaoEmCarteiraDigital = associaPaypalRequest
					.converterParaAssociacaoDeCartaoEmCarteiraDigital(userAgent, cartao.get());

			associacaoDeCartaoEmCarteiraDigitalRepository.save(associacaoDeCartaoEmCarteiraDigital);

			return ResponseEntity.created(uriComponentsBuilder.path("/cartoes/associacarteiradigital/{id}")
					.buildAndExpand(associacaoDeCartaoEmCarteiraDigital.getId()).toUri()).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(
					"{erro: Aconteceu algum erro ao bloquear o cartão no sistema legado. Tente novamente em instantes.}");
		}
	}
}