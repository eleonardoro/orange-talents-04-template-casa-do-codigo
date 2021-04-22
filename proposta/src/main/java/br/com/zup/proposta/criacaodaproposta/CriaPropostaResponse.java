package br.com.zup.proposta.criacaodaproposta;

import java.math.BigDecimal;
import java.util.UUID;

public class CriaPropostaResponse {
	private UUID id;
	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;

	public CriaPropostaResponse(Proposta proposta) {
		this.id = proposta.getId();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
	}

	public UUID getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

}