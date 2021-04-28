package br.com.zup.proposta.criacaodaproposta.acompanhamento;

import java.time.LocalDateTime;

import br.com.zup.proposta.cartao.Cartao;

public class AcompanhaPropostaCartaoResponse {
	private String id;
	private LocalDateTime emitidoEm;
	private Integer limite;

	public AcompanhaPropostaCartaoResponse(Cartao cartao) {
		this.id = cartao.getId();
		this.emitidoEm = cartao.getEmitidoEm();
		this.limite = cartao.getLimite();
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Integer getLimite() {
		return limite;
	}
	
}