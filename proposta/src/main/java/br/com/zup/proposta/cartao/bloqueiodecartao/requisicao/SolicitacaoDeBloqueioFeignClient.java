package br.com.zup.proposta.cartao.bloqueiodecartao.requisicao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${url.bloqueio.cartao}", name = "bloqueioDeCartao")
public interface SolicitacaoDeBloqueioFeignClient {

	@PostMapping(value = "${path.bloqueio.cartao}", consumes = "application/json")
	void solicitaBloqueio(@RequestBody SolicitacaoDeBloqueioRequest requisita, @PathVariable(value = "id") String id);
}