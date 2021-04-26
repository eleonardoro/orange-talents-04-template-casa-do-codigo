package br.com.zup.proposta.criacaodocartao.parcela;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.criacaodocartao.Cartao;

@Entity
public class Parcela {

	@Id
	private String id;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(nullable = false)
	private Integer valor;

	@ManyToOne
	private Cartao cartao;

	public Parcela() {
	}

	public Parcela(String id, Integer quantidade, Integer valor, Cartao cartao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Parcela [id=" + id + ", quantidade=" + quantidade + ", valor=" + valor + "]";
	}

}