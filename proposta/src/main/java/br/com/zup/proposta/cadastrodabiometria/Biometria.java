package br.com.zup.proposta.cadastrodabiometria;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import br.com.zup.proposta.criacaodocartao.Cartao;

@Entity
public class Biometria {

	@Id
	private UUID id = UUID.randomUUID();

	@Column(nullable = false, updatable = false)
	private byte[] biometria;

	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataCriacao = LocalDateTime.now();

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {
	}

	public Biometria(@NotEmpty String biometria, Cartao cartao) {
		this.biometria = biometria.getBytes();
		this.cartao = cartao;
	}

	public UUID getId() {
		return id;
	}

}