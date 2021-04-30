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
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/propostas")
public class AcompanhaPropostaController {

	PropostaRepository propostaRepository;
	private Tracer tracer;

	public AcompanhaPropostaController(PropostaRepository propostaRepository, Tracer tracer) {
		this.propostaRepository = propostaRepository;
		this.tracer = tracer;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AcompanhaPropostaResponse> detalhesDoProduto(
			@PathVariable(value = "id", required = true) String idProposta) {
		
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("proposal.id", idProposta);
		
		Optional<Proposta> proposta = propostaRepository.findById(UUID.fromString(idProposta));

		if (!proposta.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(new AcompanhaPropostaResponse(proposta.get()));
	}
}