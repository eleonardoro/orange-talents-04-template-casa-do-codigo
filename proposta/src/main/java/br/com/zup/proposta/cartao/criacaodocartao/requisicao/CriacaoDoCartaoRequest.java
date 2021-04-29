package br.com.zup.proposta.cartao.criacaodocartao.requisicao;

import javax.validation.constraints.NotEmpty;

import br.com.zup.proposta.criacaodaproposta.Proposta;

public class CriacaoDoCartaoRequest {

	@NotEmpty
	private String idProposta;

	@NotEmpty
	private String documento;

	@NotEmpty
	private String nome;

	public CriacaoDoCartaoRequest(Proposta proposta) {
		this.idProposta = proposta.getId().toString();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
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