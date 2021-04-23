package br.com.zup.proposta.healthcheck.analisedecredito;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${url.analise.proposta}", name = "solicitacaoDeAnaliseHealthCheck")
public interface AnaliseDeCreditoHealthCheckFeignClient {

	@GetMapping(value = "${path.analise.proposta.healtchek}")
	String verificaStatus();
}