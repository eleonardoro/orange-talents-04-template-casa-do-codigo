package br.com.zup.proposta.criacaodaproposta.analise;

public class SolicitacaoDeAnaliseResponse {

	private String documento;
	private String nome;
	private String resultadoSolicitacao;
	private String idProposta;

	public SolicitacaoDeAnaliseResponse(String documento, String nome, String resultadoSolicitacao, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		this.idProposta = idProposta;
	}

	@Override
	public String toString() {
		return "ResultadoAnalise [documento=" + documento + ", nome=" + nome + ", resultadoSolicitacao="
				+ resultadoSolicitacao + ", idProposta=" + idProposta + "]";
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

}