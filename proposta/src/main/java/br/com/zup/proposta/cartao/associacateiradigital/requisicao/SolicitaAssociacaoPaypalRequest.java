package br.com.zup.proposta.cartao.associacateiradigital.requisicao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SolicitaAssociacaoPaypalRequest {

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String carteira;

	public SolicitaAssociacaoPaypalRequest(@NotEmpty @Email String email, @NotEmpty String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

}