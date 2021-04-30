package br.com.zup.proposta.criacaodaproposta.acompanhamento;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.zup.proposta.criacaodaproposta.EstadoDaProposta;
import br.com.zup.proposta.criacaodaproposta.Proposta;

public class AcompanhaPropostaResponse {

	private UUID id;
	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private AcompanhaPropostaCartaoResponse cartao = null;
	private EstadoDaProposta estadoDaProposta;

	public AcompanhaPropostaResponse(Proposta proposta) {
		this.id = proposta.getId();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.estadoDaProposta = proposta.getEstadoDaProposta();

		if (this.estadoDaProposta.equals(EstadoDaProposta.CARTAO_GERADO))
			this.cartao = new AcompanhaPropostaCartaoResponse(proposta.getCartao());
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

	public AcompanhaPropostaCartaoResponse getCartao() {
		return cartao;
	}

	public EstadoDaProposta getEstadoDaProposta() {
		return estadoDaProposta;
	}

}