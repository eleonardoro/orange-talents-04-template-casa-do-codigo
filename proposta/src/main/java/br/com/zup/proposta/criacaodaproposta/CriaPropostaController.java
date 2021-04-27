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
import br.com.zup.proposta.criacaodocartao.CriacaoDoCartaoFeignClient;

@RestController
@RequestMapping("/propostas")
class CriaPropostaController {

	private PropostaRepository propostaRepository;
	private SolicitacaoDeAnaliseFeignClient solicitacaoDeAnaliseClient;
	private CriacaoDoCartaoFeignClient criacaoDoCartaoFeignClient;

	public CriaPropostaController(PropostaRepository propostaRepository,
			SolicitacaoDeAnaliseFeignClient solicitacaoDeAnaliseClient,
			CriacaoDoCartaoFeignClient criacaoDoCartaoFeignClient) {
		this.propostaRepository = propostaRepository;
		this.solicitacaoDeAnaliseClient = solicitacaoDeAnaliseClient;
		this.criacaoDoCartaoFeignClient = criacaoDoCartaoFeignClient;
	}

	@PostMapping
	public ResponseEntity<CriaPropostaResponse> criaProposta(
			@Valid @RequestBody CriaPropostaRequest criaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
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
