package br.com.zup.proposta.cartao.bloqueiodecartao.requisicao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitacaoDeBloqueioRequest {

	private String sistemaResponsavel;

	@JsonCreator
	public SolicitacaoDeBloqueioRequest(@JsonProperty("sistemaResponsavel") String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
}