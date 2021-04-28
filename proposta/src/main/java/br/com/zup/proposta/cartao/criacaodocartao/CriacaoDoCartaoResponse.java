package br.com.zup.proposta.cartao.criacaodocartao;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.criacaodocartao.vencimento.VencimentoResponse;
import br.com.zup.proposta.criacaodaproposta.Proposta;
import br.com.zup.proposta.validacoes.ExisteId;

public class CriacaoDoCartaoResponse {

	@NotEmpty
	private String id;
	
	@NotEmpty
	@ExisteId(classe = Proposta.class, atributo = "id")
	private String idProposta;
	
	@NotNull
	@PastOrPresent
	private LocalDateTime emitidoEm;
	
	@NotEmpty
	private String titular;
	
	@NotNull
	@PositiveOrZero
	private Integer limite;
	
	@NotNull
	private VencimentoResponse vencimentoResponse;

	public CriacaoDoCartaoResponse(String id, String idProposta, LocalDateTime emitidoEm, String titular, Integer limite,
			VencimentoResponse vencimento) {
		this.id = id;
		this.idProposta = idProposta;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.vencimentoResponse = vencimento;
	}

	@Override
	public String toString() {
		return "CriacaoDoCartaoResponse [id=" + id + ", idProposta=" + idProposta + ", emitidoEm=" + emitidoEm
				+ ", titular=" + titular + ", limite=" + limite + ", vencimento=" + vencimentoResponse + "]";
	}

	Cartao converterParaCartao(Proposta proposta) {
		return new Cartao(id, idProposta, emitidoEm, titular, limite, proposta);
	}

	VencimentoResponse getVencimentoResponse() {
		return vencimentoResponse;
	}

}