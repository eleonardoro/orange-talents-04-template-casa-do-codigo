package br.com.zup.proposta.cartao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.zup.proposta.cartao.criacaodocartao.aviso.Aviso;
import br.com.zup.proposta.cartao.criacaodocartao.bloqueio.Bloqueio;
import br.com.zup.proposta.cartao.criacaodocartao.carteira.Carteira;
import br.com.zup.proposta.cartao.criacaodocartao.parcela.Parcela;
import br.com.zup.proposta.cartao.criacaodocartao.renegociacao.Renegociacao;
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

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private List<Renegociacao> renegociacoes = new ArrayList<>();

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private List<Bloqueio> bloqueios = new ArrayList<>();

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private List<Aviso> avisos = new ArrayList<>();

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private List<Carteira> carteiras = new ArrayList<>();

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private List<Parcela> parcelas = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private SituacaoDoCartao situacaoDoCartao = SituacaoDoCartao.DESBLOQUEADO;

	@Deprecated
	public Cartao() {
	}

	public Cartao(String id, LocalDateTime emitidoEm, String titular, Integer limite, List<Bloqueio> bloqueios,
			List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
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