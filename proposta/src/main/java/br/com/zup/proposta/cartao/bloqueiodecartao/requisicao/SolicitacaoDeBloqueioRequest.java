package br.com.zup.proposta.cartao.bloqueiodecartao.requisicao;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitacaoDeBloqueioRequest {

	@NotEmpty
	private String sistemaResponsavel;

	@JsonCreator
	public SolicitacaoDeBloqueioRequest(@JsonProperty("sistemaResponsavel") @NotEmpty String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
}