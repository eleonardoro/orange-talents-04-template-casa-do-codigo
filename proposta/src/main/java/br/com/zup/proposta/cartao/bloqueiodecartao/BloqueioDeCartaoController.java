package br.com.zup.proposta.cartao.bloqueiodecartao;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRespository;
import br.com.zup.proposta.cartao.SituacaoDoCartao;
import br.com.zup.proposta.cartao.bloqueiodecartao.requisicao.SolicitacaoDeBloqueioFeignClient;
import br.com.zup.proposta.cartao.bloqueiodecartao.requisicao.SolicitacaoDeBloqueioRequest;

@RestController
@RequestMapping("/cartoes/bloqueio")
public class BloqueioDeCartaoController {

	BloqueioDeCartaoRepository bloqueioDeCartaoRepository;
	CartaoRespository cartaoRespository;
	SolicitacaoDeBloqueioFeignClient solicitacaoDeBloqueioFeignClient;

	public BloqueioDeCartaoController(BloqueioDeCartaoRepository bloqueioDeCartaoRepository,
			CartaoRespository cartaoRespository, SolicitacaoDeBloqueioFeignClient solicitacaoDeBloqueioFeignClient) {
		this.bloqueioDeCartaoRepository = bloqueioDeCartaoRepository;
		this.cartaoRespository = cartaoRespository;
		this.solicitacaoDeBloqueioFeignClient = solicitacaoDeBloqueioFeignClient;
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Object> bloqueiaCartao(@PathVariable(value = "id", required = true) String idCartao,
			UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request,
			@RequestHeader(value = "User-Agent") String userAgent) {

		Optional<Cartao> cartao = cartaoRespository.findById(idCartao);

		if (!cartao.isPresent())
			return ResponseEntity.notFound().build();

		if (cartao.get().getSituacaoDoCartao() == SituacaoDoCartao.BLOQUEADO)
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

		try {
			solicitacaoDeBloqueioFeignClient.solicitaBloqueio(new SolicitacaoDeBloqueioRequest(userAgent), idCartao);

			Cartao cartaoAux = cartao.get();
			
			cartaoAux.bloqueiaCartao();
			cartaoRespository.save(cartaoAux);

			bloqueioDeCartaoRepository.save(new BloqueioDeCartao(request.getRemoteAddr(), userAgent, cartaoAux));

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(
					"{erro: Aconteceu algum erro ao bloquear o cartão no sistema legado. Tente novamente em instantes.}");
		}
	}
}