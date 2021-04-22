package br.com.zup.proposta.criacaodaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.proposta.validacoes.CpfOuCnpj;
import br.com.zup.proposta.validacoes.ValorUnico;

class CriaPropostaRequest {

	@NotEmpty
	@CpfOuCnpj
	@ValorUnico(classe = Proposta.class, atributo = "documento")
	private String documento;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	public CriaPropostaRequest(@NotEmpty String documento, @NotEmpty @Email String email, @NotEmpty String nome,
			@NotEmpty String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	Proposta converterParaProposta() {
		return new Proposta(documento, email, nome, endereco, salario);
	}
}