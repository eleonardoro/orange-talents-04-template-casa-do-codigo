package br.com.zup.proposta.cartao.criacaodocartao.vencimento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Vencimento {

	@Id
	private String id;

	@Column(nullable = false)
	private Integer dia;

	@Column(nullable = false)
	private LocalDateTime dataDeCriacao;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Vencimento() {
	}

	public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao, Cartao cartao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Vencimento [id=" + id + ", dia=" + dia + ", dataDeCriacao=" + dataDeCriacao + "]";
	}

}