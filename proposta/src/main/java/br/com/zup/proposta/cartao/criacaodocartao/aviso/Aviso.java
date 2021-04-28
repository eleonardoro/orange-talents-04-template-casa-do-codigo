package br.com.zup.proposta.cartao.criacaodocartao.aviso;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Aviso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate validoAte;

	@Column(nullable = false)
	private String destino;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Aviso() {
	}

	public Aviso(LocalDate validoAte, String destino, Cartao cartao) {
		this.validoAte = validoAte;
		this.destino = destino;
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Aviso [id=" + id + ", validoAte=" + validoAte + ", destino=" + destino + "]";
	}
	
	

}