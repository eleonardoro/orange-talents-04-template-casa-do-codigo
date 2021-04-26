package br.com.zup.proposta.criacaodocartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${url.criacao.do.cartao}", name = "solicitacaoDeCriacaoDoCartao")
public interface CriacaoDoCartaoFeignClient {

	@PostMapping(value = "${path.criacao.do.cartao}", consumes = "application/json")
	CriacaoDoCartaoResponse solicitaCartao(@RequestBody CriacaoDoCartaoRequest requisita);
}