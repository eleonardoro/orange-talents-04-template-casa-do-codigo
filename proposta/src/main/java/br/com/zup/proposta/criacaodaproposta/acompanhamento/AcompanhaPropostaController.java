package br.com.zup.proposta.criacaodaproposta.acompanhamento;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.criacaodaproposta.Proposta;
import br.com.zup.proposta.criacaodaproposta.PropostaRepository;

@RestController
@RequestMapping("/propostas")
public class AcompanhaPropostaController {

	PropostaRepository propostaRepository;

	public AcompanhaPropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AcompanhaPropostaResponse> detalhesDoProduto(
			@PathVariable(value = "id", required = true) String idProduto) {
		Optional<Proposta> proposta = propostaRepository.findById(UUID.fromString(idProduto));

		if (!proposta.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(new AcompanhaPropostaResponse(proposta.get()));
	}
}