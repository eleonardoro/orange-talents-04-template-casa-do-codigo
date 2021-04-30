package br.com.zup.proposta.cartao.avisodeviagem;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import br.com.zup.proposta.cartao.avisodeviagem.requisicao.SolicitacaoDeAvisoFeignClient;
import br.com.zup.proposta.cartao.avisodeviagem.requisicao.SolicitacaoDeAvisoRequest;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/cartoes/avisodeviagem")
public class AvisoDeViagemController {

	CartaoRespository cartaoRespository;
	AvisoDeViagemRepository avisoDeViagemRepository;
	SolicitacaoDeAvisoFeignClient solicitacaoDeAvisoFeignClient;
	private Tracer tracer;

	public AvisoDeViagemController(CartaoRespository cartaoRespository, AvisoDeViagemRepository avisoDeViagemRepository,
			SolicitacaoDeAvisoFeignClient solicitacaoDeAvisoFeignClient, Tracer tracer) {
		this.cartaoRespository = cartaoRespository;
		this.avisoDeViagemRepository = avisoDeViagemRepository;
		this.solicitacaoDeAvisoFeignClient = solicitacaoDeAvisoFeignClient;
		this.tracer = tracer;
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Object> avisaViagem(@PathVariable(value = "id", required = true) String idCartao,
			UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request,
			@RequestHeader(value = "User-Agent") String userAgent,
			@Valid @RequestBody AvisoDeViagemRequest avisoDeViagemRequest) {
		
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("card.id", idCartao);

		Optional<Cartao> cartao = cartaoRespository.findById(idCartao);

		if (!cartao.isPresent())
			return ResponseEntity.notFound().build();
		try {

			solicitacaoDeAvisoFeignClient.criaAvisoDeViagem(new SolicitacaoDeAvisoRequest(
					avisoDeViagemRequest.getDestino(), avisoDeViagemRequest.getValidoAte()), idCartao);

			AvisoDeViagem avisoDeViagem = avisoDeViagemRequest.converterParaAvisoDeViagem(cartao.get(), userAgent,
					request.getRemoteAddr());

			avisoDeViagemRepository.save(avisoDeViagem);

			return ResponseEntity.ok().build();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(
					"{erro: Aconteceu algum erro ao bloquear o cartão no sistema legado. Tente novamente em instantes.}");
		}
	}
}