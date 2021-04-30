package br.com.zup.proposta.cartao.associacateiradigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.associacateiradigital.carteira.AssociacaoDeCartaoEmCarteiraDigital;
import br.com.zup.proposta.cartao.associacateiradigital.requisicao.SolicitaAssociacaoCarteiraDigitalRequest;

public class AssociaPaypalRequest implements AssociaCarteiraRequest {

	@NotEmpty
	@Email
	private String email;

	@JsonCreator
	public AssociaPaypalRequest(@NotEmpty @Email @JsonProperty("email") String email) {
		this.email = email;
	}

	@Override
	public SolicitaAssociacaoCarteiraDigitalRequest converterParaSolicitaAssociacaoCarteiraDigitalRequest() {
		return new SolicitaAssociacaoCarteiraDigitalRequest("email", CarteirasDigitais.PAYPAL.toString());
	}

	@Override
	public AssociacaoDeCartaoEmCarteiraDigital converterParaAssociacaoDeCartaoEmCarteiraDigital(String userAgent,
			Cartao cartao) {
		return new AssociacaoDeCartaoEmCarteiraDigital(email, userAgent, cartao, CarteirasDigitais.PAYPAL);
	}
}