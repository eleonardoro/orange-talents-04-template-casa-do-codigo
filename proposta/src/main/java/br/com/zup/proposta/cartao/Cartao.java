package br.com.zup.proposta.cartao;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.zup.proposta.criacaodaproposta.Proposta;

@Entity
public class Cartao {

	@Id
	private String id;

	@Column(nullable = false)
	private LocalDateTime emitidoEm;

	@Column(nullable = false)
	private String titular;

	@Column(nullable = false)
	private Integer limite;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "proposta_id", referencedColumnName = "id")
	Proposta proposta;

	@Enumerated(EnumType.STRING)
	private SituacaoDoCartao situacaoDoCartao = SituacaoDoCartao.DESBLOQUEADO;

	@Deprecated
	public Cartao() {
	}

	public Cartao(String id, LocalDateTime emitidoEm, String titular, Integer limite, Proposta proposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Integer getLimite() {
		return limite;
	}

	public SituacaoDoCartao getSituacaoDoCartao() {
		return situacaoDoCartao;
	}

	public void bloqueiaCartao() {
		this.situacaoDoCartao = SituacaoDoCartao.BLOQUEADO;
	}
}