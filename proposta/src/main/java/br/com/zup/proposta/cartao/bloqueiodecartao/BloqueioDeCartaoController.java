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

@RestController
@RequestMapping("/cartoes/bloqueio")
public class BloqueioDeCartaoController {

	BloqueioDeCartaoRepository bloqueioDeCartaoRepository;
	CartaoRespository cartaoRespository;

	public BloqueioDeCartaoController(BloqueioDeCartaoRepository bloqueioDeCartaoRepository,
			CartaoRespository cartaoRespository) {
		this.bloqueioDeCartaoRepository = bloqueioDeCartaoRepository;
		this.cartaoRespository = cartaoRespository;
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

		cartao.get().bloqueiaCartao();
		cartaoRespository.save(cartao.get());

		bloqueioDeCartaoRepository.save(new BloqueioDeCartao(request.getRemoteAddr(), userAgent, cartao.get()));

		return ResponseEntity.ok().build();
	}
}