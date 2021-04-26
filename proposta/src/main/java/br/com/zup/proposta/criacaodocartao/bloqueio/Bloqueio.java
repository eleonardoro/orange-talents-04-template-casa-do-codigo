package br.com.zup.proposta.criacaodocartao.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.criacaodocartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	private String id;

	@Column(nullable = false)
	private LocalDateTime bloqueadoEm;

	@Column(nullable = false)
	private String sistemaResponsavel;

	@Column(nullable = false)
	private boolean ativo;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Bloqueio() {
		super();
	}

	public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo, Cartao cartao) {
		this.id = id;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Bloqueio [id=" + id + ", bloqueadoEm=" + bloqueadoEm + ", sistemaResponsavel=" + sistemaResponsavel
				+ ", ativo=" + ativo + "]";
	}

}