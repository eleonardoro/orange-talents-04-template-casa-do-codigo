package br.com.zup.proposta.cartao.avisodeviagem.requisicao;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SolicitacaoDeAvisoRequest {

	@NotEmpty
	private String destino;

	@NotNull
	@FutureOrPresent
	private LocalDate validoAte;

	public SolicitacaoDeAvisoRequest(@NotEmpty String destino, @FutureOrPresent LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

}