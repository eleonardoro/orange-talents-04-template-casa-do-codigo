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
	private LocalDateTime instanteDeBloqueio = LocalDateTime.now();

	@Column(nullable = false, updatable = false)
	private String ip;

	@Column(nullable = false, updatable = false)
	private String userAgent;
	
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public BloqueioDeCartao() {
	}

	public BloqueioDeCartao(String ip, String userAgent, Cartao cartao) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

}