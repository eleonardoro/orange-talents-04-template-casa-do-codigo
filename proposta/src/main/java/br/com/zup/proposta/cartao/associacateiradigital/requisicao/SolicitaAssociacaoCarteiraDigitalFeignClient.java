package br.com.zup.proposta.cartao.associacateiradigital.requisicao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${url.associa.carteira.digital.cartao}", name = "associaCartaoEmCarteiraDigital")
public interface SolicitaAssociacaoCarteiraDigitalFeignClient {

	@PostMapping(value = "${path.associa.carteira.digital.cartao}", consumes = "application/json")
	public void associaPaypal(@RequestBody SolicitaAssociacaoCarteiraDigitalRequest requisita, @PathVariable(value = "id") String id);
}