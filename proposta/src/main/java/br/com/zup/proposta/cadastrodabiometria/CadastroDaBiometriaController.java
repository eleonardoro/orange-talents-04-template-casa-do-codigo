package br.com.zup.proposta.cadastrodabiometria;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.criacaodocartao.Cartao;
import br.com.zup.proposta.criacaodocartao.CartaoRespository;

@RestController
@RequestMapping("/cartoes/biometria")
public class CadastroDaBiometriaController {

	CartaoRespository cartaoRespository;
	BiometriaRepository biometriaRepository;

	public CadastroDaBiometriaController(CartaoRespository cartaoRespository, BiometriaRepository biometriaRepository) {
		this.cartaoRespository = cartaoRespository;
		this.biometriaRepository = biometriaRepository;
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Object> detalhesDoProduto(
			@PathVariable(value = "id", required = true) String idCartao,
			@Valid @RequestBody CadastroDaBiometriaRequest cadastroDaBiometriaRequest,
			UriComponentsBuilder uriComponentsBuilder) {

		Optional<Cartao> cartao = cartaoRespository.findById(idCartao);

		if (!cartao.isPresent())
			return ResponseEntity.notFound().build();

		Biometria biometria = cadastroDaBiometriaRequest.converterParaBiometria(cartao.get());
		biometriaRepository.save(biometria);

		return ResponseEntity
				.created(uriComponentsBuilder.path("/cartoes/biometria/{id}")
				.buildAndExpand(biometria.getId()).toUri()).build();
	}
}