package br.com.zup.proposta.criacaodaproposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${url.analise.proposta}", name = "solicitacaoDeAnalise")
public interface SolicitacaoDeAnaliseFeignClient {

	@PostMapping(value = "${path.analise.proposta}", consumes = "application/json")
	SolicitacaoDeAnaliseResponse solicitaAnalise(@RequestBody SolicitacaoDeAnaliseRequest requisita);
}