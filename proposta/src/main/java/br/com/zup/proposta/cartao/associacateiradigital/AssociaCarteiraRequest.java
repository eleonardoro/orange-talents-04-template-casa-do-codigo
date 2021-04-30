package br.com.zup.proposta.cartao.associacateiradigital;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.associacateiradigital.carteira.AssociacaoDeCartaoEmCarteiraDigital;
import br.com.zup.proposta.cartao.associacateiradigital.requisicao.SolicitaAssociacaoCarteiraDigitalRequest;

public interface AssociaCarteiraRequest {

	public SolicitaAssociacaoCarteiraDigitalRequest converterParaSolicitaAssociacaoCarteiraDigitalRequest();

	public AssociacaoDeCartaoEmCarteiraDigital converterParaAssociacaoDeCartaoEmCarteiraDigital(String userAgent,
			Cartao cartao);
}