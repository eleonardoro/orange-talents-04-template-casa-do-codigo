package br.com.zup.proposta.criacaodaproposta.analise;

import br.com.zup.proposta.criacaodaproposta.Proposta;

public class RequisitaAnaliseRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public RequisitaAnaliseRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}