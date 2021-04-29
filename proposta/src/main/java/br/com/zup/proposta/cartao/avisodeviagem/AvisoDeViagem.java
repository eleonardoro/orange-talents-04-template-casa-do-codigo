package br.com.zup.proposta.cartao.avisodeviagem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class AvisoDeViagem {

	@Id
	private UUID id = UUID.randomUUID();

	@Column(nullable = false)
	private LocalDate validoAte;

	@Column(nullable = false)
	private String destino;

	@ManyToOne
	private Cartao cartao;

	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataDoAviso = LocalDateTime.now();

	@Column(nullable = false, updatable = false)
	private String ip;

	@Column(nullable = false, updatable = false)
	private String userAgent;

	@Deprecated
	public AvisoDeViagem() {
	}

	public AvisoDeViagem(LocalDate validoAte, String destino, Cartao cartao, String ip, String userAgent) {
		this.validoAte = validoAte;
		this.destino = destino;
		this.cartao = cartao;
		this.ip = ip;
		this.userAgent = userAgent;
	}
}