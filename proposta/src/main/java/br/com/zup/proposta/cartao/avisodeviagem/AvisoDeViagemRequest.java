package br.com.zup.proposta.cartao.avisodeviagem;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.zup.proposta.cartao.Cartao;

public class AvisoDeViagemRequest {

	@NotEmpty
	private String destino;

	@FutureOrPresent
	@NotNull
	private LocalDate validoAte;

	public AvisoDeViagemRequest(@NotEmpty String destino, @FutureOrPresent LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public AvisoDeViagem converterParaAvisoDeViagem(Cartao cartao, String userAgent, String ip) {
		return new AvisoDeViagem(validoAte, destino, cartao, ip, userAgent);
	}
}