package br.com.zup.proposta.criacaodaproposta;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.criacaodaproposta.analise.SolicitacaoDeAnaliseFeignClient;
import br.com.zup.proposta.criacaodaproposta.analise.SolicitacaoDeAnaliseRequest;
import br.com.zup.proposta.criacaodaproposta.analise.SolicitacaoDeAnaliseResponse;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/propostas")
class CriaPropostaController {

	private PropostaRepository propostaRepository;
	private SolicitacaoDeAnaliseFeignClient solicitacaoDeAnaliseClient;
	private Tracer tracer;

	public CriaPropostaController(PropostaRepository propostaRepository,
			SolicitacaoDeAnaliseFeignClient solicitacaoDeAnaliseClient, Tracer tracer) {
		this.propostaRepository = propostaRepository;
		this.solicitacaoDeAnaliseClient = solicitacaoDeAnaliseClient;
		this.tracer = tracer;
	}

	@PostMapping
	public ResponseEntity<CriaPropostaResponse> criaProposta(
			@Valid @RequestBody CriaPropostaRequest criaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("user.email", criaPropostaRequest.getEmail());

		Proposta proposta = criaPropostaRequest.converterParaProposta();
		try {
			SolicitacaoDeAnaliseResponse resultadoAnalise = solicitacaoDeAnaliseClient
					.solicitaAnalise(new SolicitacaoDeAnaliseRequest(proposta));

			proposta.setEstadoDaProposta(resultadoAnalise.getResultadoSolicitacao());

			propostaRepository.save(proposta);

			return ResponseEntity
					.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri())
					.body(new CriaPropostaResponse(proposta));
		} catch (Exception e) {
			proposta.setEstadoDaProposta(EstadoDaProposta.NAO_ELEGIVEL);
			propostaRepository.save(proposta);

			return ResponseEntity.badRequest().build();
		}
	}
}
