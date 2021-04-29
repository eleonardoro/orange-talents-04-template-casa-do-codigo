package br.com.zup.proposta.cartao.avisodeviagem.requisicao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${url.aviso.de.viagem.cartao}", name = "avisoDeViagem")
public interface SolicitacaoDeAvisoFeignClient {

	@PostMapping(value = "${path.aviso.de.viagem.cartao}", consumes = "application/json")
	void criaAvisoDeViagem(@RequestBody SolicitacaoDeAvisoRequest requisita, @PathVariable(value = "id") String id);
}