package br.com.zup.proposta.cartao.associacateiradigital.requisicao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${url.associa.paypal.cartao}", name = "associaCartaoPaypal")
public interface SolicitaAssociacaoPaypalFeignClient {

	@PostMapping(value = "${path.aviso.de.viagem.cartao}", consumes = "application/json")
	public void associaPaypal(@RequestBody SolicitaAssociacaoPaypalRequest requisita, @PathVariable(value = "id") String id);
}