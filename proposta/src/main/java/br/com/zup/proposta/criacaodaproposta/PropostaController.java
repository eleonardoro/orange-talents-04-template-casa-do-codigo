package br.com.zup.proposta.criacaodaproposta;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
class PropostaController {

	PropostaRepository propostaRepository;

	public PropostaController(PropostaRepository propostaRepository) {
		super();
		this.propostaRepository = propostaRepository;
	}

	@PostMapping
	public ResponseEntity<CriaPropostaResponse> criaProposta(@Valid @RequestBody CriaPropostaRequest criaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
		Proposta proposta = criaPropostaRequest.converterParaProposta();
		
		propostaRepository.save(proposta);
		
		return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri())
				.body(new CriaPropostaResponse(proposta));
	}
}
