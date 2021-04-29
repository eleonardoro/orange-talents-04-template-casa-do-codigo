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

@RestController
@RequestMapping("/cartoes/avisodeviagem")
public class AvisoDeViagemController {

	CartaoRespository cartaoRespository;
	AvisoDeViagemRepository avisoDeViagemRepository;

	public AvisoDeViagemController(CartaoRespository cartaoRespository,
			AvisoDeViagemRepository avisoDeViagemRepository) {
		this.cartaoRespository = cartaoRespository;
		this.avisoDeViagemRepository = avisoDeViagemRepository;
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Object> avisaViagem(@PathVariable(value = "id", required = true) String idCartao,
			UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request,
			@RequestHeader(value = "User-Agent") String userAgent,
			@Valid @RequestBody AvisoDeViagemRequest avisoDeViagemRequest) {

		Optional<Cartao> cartao = cartaoRespository.findById(idCartao);

		if (!cartao.isPresent())
			return ResponseEntity.notFound().build();

		AvisoDeViagem avisoDeViagem = avisoDeViagemRequest.converterParaAvisoDeViagem(cartao.get(), userAgent,
				request.getRemoteAddr());

		avisoDeViagemRepository.save(avisoDeViagem);

		return ResponseEntity.ok().build();
	}
}