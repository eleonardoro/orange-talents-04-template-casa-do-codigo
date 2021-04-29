package br.com.zup.proposta.cartao.associacateiradigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.associacateiradigital.carteira.AssociacaoDeCartaoEmCarteiraDigital;
import br.com.zup.proposta.cartao.associacateiradigital.requisicao.SolicitaAssociacaoPaypalRequest;

public class AssociaPaypalRequest {

	@NotEmpty
	@Email
	private String email;

	@JsonCreator
	public AssociaPaypalRequest(@NotEmpty @Email @JsonProperty("email") String email) {
		this.email = email;
	}

	public SolicitaAssociacaoPaypalRequest converterParaSolicitaAssociacaoPaypalRequest() {
		return new SolicitaAssociacaoPaypalRequest("email", CarteirasDigitais.PAYPAL.toString());
	}

	public AssociacaoDeCartaoEmCarteiraDigital converterParaAssociacaoDeCartaoEmCarteiraDigital(String userAgent, Cartao cartao) {
		return new AssociacaoDeCartaoEmCarteiraDigital(email, userAgent, cartao, CarteirasDigitais.PAYPAL);
	}
}