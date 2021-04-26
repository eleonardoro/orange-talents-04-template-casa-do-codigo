package br.com.zup.proposta.criacaodocartao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.zup.proposta.criacaodaproposta.Proposta;
import br.com.zup.proposta.criacaodocartao.aviso.Aviso;
import br.com.zup.proposta.criacaodocartao.bloqueio.Bloqueio;
import br.com.zup.proposta.criacaodocartao.carteira.Carteira;
import br.com.zup.proposta.criacaodocartao.parcela.Parcela;
import br.com.zup.proposta.criacaodocartao.renegociacao.Renegociacao;

@Entity
public class Cartao {

	@Id
	private String id;

	@Column(nullable = false)
	private String idProposta;

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

	@Deprecated
	public Cartao() {
	}

	public Cartao(String id, String idProposta, LocalDateTime emitidoEm, String titular, Integer limite,
			List<Bloqueio> bloqueios, List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas) {
		this.id = id;
		this.idProposta = idProposta;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
	}

	public Cartao(String id, String idProposta, LocalDateTime emitidoEm, String titular, Integer limite, Proposta proposta) {
		this.id = id;
		this.idProposta = idProposta;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

}
