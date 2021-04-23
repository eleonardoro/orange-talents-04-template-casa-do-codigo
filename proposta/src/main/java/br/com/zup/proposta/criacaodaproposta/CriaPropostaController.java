package br.com.zup.proposta.criacaodaproposta;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.criacaodaproposta.analise.RequisitaAnaliseRequest;
import br.com.zup.proposta.criacaodaproposta.analise.RequisitaAnaliseResponse;
import br.com.zup.proposta.criacaodaproposta.analise.SolicitacaoDeAnaliseFeignClient;

@RestController
@RequestMapping("/propostas")
class CriaPropostaController {

	private PropostaRepository propostaRepository;
	private SolicitacaoDeAnaliseFeignClient solicitacaoDeAnaliseClient;

	public CriaPropostaController(PropostaRepository propostaRepository,
			SolicitacaoDeAnaliseFeignClient solicitacaoDeAnaliseClient) {
		this.propostaRepository = propostaRepository;
		this.solicitacaoDeAnaliseClient = solicitacaoDeAnaliseClient;
	}

	@PostMapping
	public ResponseEntity<CriaPropostaResponse> criaProposta(@Valid @RequestBody CriaPropostaRequest criaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
		Proposta proposta = criaPropostaRequest.converterParaProposta();
		
		RequisitaAnaliseResponse resultadoAnalise = solicitacaoDeAnaliseClient.solicitaAnalise(new RequisitaAnaliseRequest(proposta));
		
		proposta.setEstadoDaProposta(resultadoAnalise.getResultadoSolicitacao());
		
		propostaRepository.save(proposta);
		
		return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri())
				.body(new CriaPropostaResponse(proposta));
	}
}
