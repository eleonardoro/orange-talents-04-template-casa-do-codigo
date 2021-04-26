package br.com.zup.proposta.criacaodocartao.vencimento;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class VencimentoResponse {

	@NotEmpty
	private String id;
	
	@NotNull
	@Min(1)
	@Max(31)
	private Integer dia;
	
	@NotNull
	@FutureOrPresent
	private LocalDateTime dataDeCriacao;

	public VencimentoResponse(String id, Integer dia, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	@Override
	public String toString() {
		return "VencimentoResponse [id=" + id + ", dia=" + dia + ", dataDeCriacao=" + dataDeCriacao + "]";
	}

	public Vencimento converterParaVencimento() {
		return new Vencimento(id, dia, dataDeCriacao);
	}

	public String getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

}