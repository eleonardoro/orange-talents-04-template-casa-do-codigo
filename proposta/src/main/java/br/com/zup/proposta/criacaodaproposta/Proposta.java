package br.com.zup.proposta.criacaodaproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.springframework.security.crypto.encrypt.Encryptors;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Proposta {

	@Id
	@Type(type = "uuid-char")
	private final UUID id = UUID.randomUUID();

	@Column(nullable = false)
	private String documento;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private BigDecimal salario;

	@OneToOne(mappedBy = "proposta")
	private Cartao cartao;

	@Enumerated(EnumType.STRING)
	private EstadoDaProposta estadoDaProposta;

	@Column(nullable = false, updatable = false)
	private final LocalDateTime dataCriacao = LocalDateTime.now();

	@Deprecated
	public Proposta() {
	}

	public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario, String password,
			String salt) {
		this.documento = Encryptors.text(password, salt).encrypt(documento);
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public UUID getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public EstadoDaProposta getEstadoDaProposta() {
		return estadoDaProposta;
	}

	public void setEstadoDaProposta(String estado) {
		this.estadoDaProposta = estado.equals("SEM_RESTRICAO") ? EstadoDaProposta.ELEGIVEL
				: EstadoDaProposta.NAO_ELEGIVEL;
	}

	public void setEstadoDaProposta(EstadoDaProposta estadoDaProposta) {
		this.estadoDaProposta = estadoDaProposta;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
}