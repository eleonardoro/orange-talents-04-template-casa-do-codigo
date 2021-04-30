package br.com.zup.proposta.cartao.associacateiradigital;

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
import br.com.zup.proposta.cartao.associacateiradigital.requisicao.SolicitaAssociacaoCarteiraDigitalFeignClient;
import br.com.zup.proposta.cartao.associacateiradigital.requisicao.SolicitaAssociacaoCarteiraDigitalRequest;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/cartoes/associacarteiradigital")
public class AssociaCarteiraController {

	AssociacaoDeCartaoEmCarteiraDigitalRepository associacaoDeCartaoEmCarteiraDigitalRepository;
	SolicitaAssociacaoCarteiraDigitalFeignClient associaPaypalFeignClient;
	CartaoRespository cartaoRespository;
	private Tracer tracer;

	public AssociaCarteiraController(
			AssociacaoDeCartaoEmCarteiraDigitalRepository associacaoDeCartaoEmCarteiraDigitalRepository,
			SolicitaAssociacaoCarteiraDigitalFeignClient associaPaypalFeignClient, CartaoRespository cartaoRespository,
			Tracer tracer) {
		this.associacaoDeCartaoEmCarteiraDigitalRepository = associacaoDeCartaoEmCarteiraDigitalRepository;
		this.associaPaypalFeignClient = associaPaypalFeignClient;
		this.cartaoRespository = cartaoRespository;
		this.tracer = tracer;
	}

	@PostMapping("/paypal/{id}")
	public ResponseEntity<Object> associaNaCarteiraDigitalPaypal(
			@PathVariable(value = "id", required = true) String idCartao,
			@RequestBody @Valid AssociaPaypalRequest associaPaypalRequest,
			@RequestHeader(value = "User-Agent") String userAgent, UriComponentsBuilder uriComponentsBuilder) {

		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("card.id", idCartao);

		return cadastraCarteiraDigital(idCartao, associaPaypalRequest, userAgent, uriComponentsBuilder,
				CarteirasDigitais.PAYPAL);
	}

	@PostMapping("/samsungpay/{id}")
	public ResponseEntity<Object> associaNaCarteiraDigitalSamsungpay(
			@PathVariable(value = "id", required = true) String idCartao,
			@RequestBody @Valid AssociaSamsungpayRequest associaSamsungPayRequest,
			@RequestHeader(value = "User-Agent") String userAgent, UriComponentsBuilder uriComponentsBuilder) {

		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("card.id", idCartao);

		return cadastraCarteiraDigital(idCartao, associaSamsungPayRequest, userAgent, uriComponentsBuilder,
				CarteirasDigitais.SAMSUNGPAY);
	}

	private ResponseEntity<Object> cadastraCarteiraDigital(String idCartao,
			AssociaCarteiraRequest associaCarteiraPayRequest, String userAgent,
			UriComponentsBuilder uriComponentsBuilder, CarteirasDigitais carteira) {
		Optional<Cartao> cartao = cartaoRespository.findById(idCartao);

		if (!cartao.isPresent())
			return ResponseEntity.notFound().build();

		if (!associacaoDeCartaoEmCarteiraDigitalRepository.findByCartao_IdAndCarteira(idCartao, carteira).isEmpty())
			return ResponseEntity.unprocessableEntity().build();

		try {
			SolicitaAssociacaoCarteiraDigitalRequest solicitaAssociacaoPaypalRequest = associaCarteiraPayRequest
					.converterParaSolicitaAssociacaoCarteiraDigitalRequest();
			associaPaypalFeignClient.associaPaypal(solicitaAssociacaoPaypalRequest, idCartao);

			AssociacaoDeCartaoEmCarteiraDigital associacaoDeCartaoEmCarteiraDigital = associaCarteiraPayRequest
					.converterParaAssociacaoDeCartaoEmCarteiraDigital(userAgent, cartao.get());

			associacaoDeCartaoEmCarteiraDigitalRepository.save(associacaoDeCartaoEmCarteiraDigital);

			return ResponseEntity.created(uriComponentsBuilder
					.path("/cartoes/associacarteiradigital/" + carteira.toString().toLowerCase() + "/{id}")
					.buildAndExpand(associacaoDeCartaoEmCarteiraDigital.getId()).toUri()).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(
					"{erro: Aconteceu algum erro ao bloquear o cartão no sistema legado. Tente novamente em instantes.}");
		}
	}
}