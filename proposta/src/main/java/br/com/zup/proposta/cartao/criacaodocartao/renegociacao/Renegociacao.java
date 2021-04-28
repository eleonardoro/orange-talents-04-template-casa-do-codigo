package br.com.zup.proposta.cartao.criacaodocartao.renegociacao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Renegociacao {

	@Id
	private String id;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private Integer valor;

	@Column(nullable = false)
	private LocalDateTime dataDeCriacao;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Renegociacao() {
	}

	public Renegociacao(String id, Integer quantidade, Integer valor, LocalDateTime dataDeCriacao, Cartao cartao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Renegociacao [id=" + id + ", quantidade=" + quantidade + ", valor=" + valor + ", dataDeCriacao="
				+ dataDeCriacao + "]";
	}

}