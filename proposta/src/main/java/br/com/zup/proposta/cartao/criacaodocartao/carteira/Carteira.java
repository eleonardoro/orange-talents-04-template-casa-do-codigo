package br.com.zup.proposta.cartao.criacaodocartao.carteira;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	private String id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private LocalDateTime associadaEm;

	@Column(nullable = false)
	private String emissor;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Carteira() {
	}

	public Carteira(String id, String email, LocalDateTime associadaEm, String emissor, Cartao cartao) {
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Carteira [id=" + id + ", email=" + email + ", associadaEm=" + associadaEm + ", emissor=" + emissor
				+ "]";
	}

}