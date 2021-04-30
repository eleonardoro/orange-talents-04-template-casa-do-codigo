package br.com.zup.proposta.cartao.biometria;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRespository;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/cartoes/biometria")
public class CadastroDaBiometriaController {

	CartaoRespository cartaoRespository;
	BiometriaRepository biometriaRepository;
	private Tracer tracer;

	public CadastroDaBiometriaController(CartaoRespository cartaoRespository, BiometriaRepository biometriaRepository,
			Tracer tracer) {
		this.cartaoRespository = cartaoRespository;
		this.biometriaRepository = biometriaRepository;
		this.tracer = tracer;
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Object> detalhesDoProduto(@PathVariable(value = "id", required = true) String idCartao,
			@Valid @RequestBody CadastroDaBiometriaRequest cadastroDaBiometriaRequest,
			UriComponentsBuilder uriComponentsBuilder) {
		
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("card.id", idCartao);

		Optional<Cartao> cartao = cartaoRespository.findById(idCartao);

		if (!cartao.isPresent())
			return ResponseEntity.notFound().build();

		Biometria biometria = cadastroDaBiometriaRequest.converterParaBiometria(cartao.get());
		biometriaRepository.save(biometria);

		return ResponseEntity
				.created(uriComponentsBuilder.path("/cartoes/biometria/{id}").buildAndExpand(biometria.getId()).toUri())
				.build();
	}
}