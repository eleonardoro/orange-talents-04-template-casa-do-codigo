package br.com.zup.proposta.cartao.associacateiradigital.carteira;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.associacateiradigital.CarteirasDigitais;

@Entity
public class AssociacaoDeCartaoEmCarteiraDigital {

	@Id
	private UUID id = UUID.randomUUID();

	@Column(nullable = false)
	private String email;

	@Column(nullable = false, updatable = false)
	private LocalDateTime associadaEm = LocalDateTime.now();

	@Column(nullable = false)
	private String emissor;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CarteirasDigitais carteira;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public AssociacaoDeCartaoEmCarteiraDigital() {
	}

	public AssociacaoDeCartaoEmCarteiraDigital(String email, String emissor, Cartao cartao, CarteirasDigitais carteira) {
		this.email = email;
		this.emissor = emissor;
		this.cartao = cartao;
		this.carteira = carteira;
	}

	@Override
	public String toString() {
		return "Carteira [id=" + id + ", email=" + email + ", associadaEm=" + associadaEm + ", emissor=" + emissor
				+ "]";
	}

	public UUID getId() {
		return id;
	}
	
	

}