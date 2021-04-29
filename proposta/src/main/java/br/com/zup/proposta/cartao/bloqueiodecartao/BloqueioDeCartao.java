package br.com.zup.proposta.cartao.bloqueiodecartao;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class BloqueioDeCartao {

	@Id
	private UUID id = UUID.randomUUID();

	@Column(nullable = false, updatable = false)
	private LocalDateTime bloqueadoEm = LocalDateTime.now();

	@Column(nullable = false, updatable = false)
	private String ip;

	@Column(nullable = false, updatable = false)
	private String sistemaResponsavel;

	@Column(nullable = false)
	private boolean ativo = true;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public BloqueioDeCartao() {
	}

	public BloqueioDeCartao(String ip, String sistemaResponsavel, Cartao cartao) {
		this.ip = ip;
		this.sistemaResponsavel = sistemaResponsavel;
		this.cartao = cartao;
	}

}