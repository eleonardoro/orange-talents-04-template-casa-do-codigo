package br.com.zup.proposta.criacaodocartao;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.zup.proposta.criacaodaproposta.EstadoDaProposta;
import br.com.zup.proposta.criacaodaproposta.Proposta;
import br.com.zup.proposta.criacaodaproposta.PropostaRepository;
import br.com.zup.proposta.criacaodocartao.vencimento.Vencimento;
import br.com.zup.proposta.criacaodocartao.vencimento.VencimentoRepository;
import br.com.zup.proposta.criacaodocartao.vencimento.VencimentoResponse;

@Configuration
@EnableScheduling
public class CriaCartaoJob {

	PropostaRepository propostaRepository;
	CartaoRespository cartaoRepository;
	VencimentoRepository vencimentoRepository;
	CriacaoDoCartaoFeignClient criacaoDoCartaoFeignClient;

	public CriaCartaoJob(PropostaRepository propostaRepository, CartaoRespository cartaoRepository,
			VencimentoRepository vencimentoRepository, CriacaoDoCartaoFeignClient criacaoDoCartaoFeignClient) {
		this.propostaRepository = propostaRepository;
		this.cartaoRepository = cartaoRepository;
		this.vencimentoRepository = vencimentoRepository;
		this.criacaoDoCartaoFeignClient = criacaoDoCartaoFeignClient;
	}

	@Scheduled(fixedDelayString =  "${cartao.solicitacao.delay}")
	public void solicitaCartoesParaPropostasNoEstadoElegivel() {
		List<Proposta> propostas = propostaRepository
				.findByEstadoDaPropostaIn(List.of(EstadoDaProposta.ELEGIVEL, EstadoDaProposta.CARTAO_SOLICITADO));

		for (Proposta proposta : propostas) {

			try {
				CriacaoDoCartaoResponse criacaoDoCartaoResponse = criacaoDoCartaoFeignClient
						.solicitaCartao(new CriacaoDoCartaoRequest(proposta));

				Cartao cartao = cartaoRepository.save(criacaoDoCartaoResponse.converterParaCartao(proposta));

				VencimentoResponse vencimentoResponse = criacaoDoCartaoResponse.getVencimentoResponse();
				vencimentoRepository.save(new Vencimento(vencimentoResponse.getId(), vencimentoResponse.getDia(),
						vencimentoResponse.getDataDeCriacao(), cartao));

				proposta.setEstadoDaProposta(EstadoDaProposta.CARTAO_GERADO);
				proposta.setCartao(cartao);
				propostaRepository.save(proposta);

			} catch (Exception e) {
				if (proposta.getEstadoDaProposta().equals(EstadoDaProposta.ELEGIVEL)) {
					proposta.setEstadoDaProposta(EstadoDaProposta.CARTAO_SOLICITADO);
					propostaRepository.save(proposta);
				}

				System.out.println("DEU ERROOOOOOOOOOOO");
				System.out.println(e.getMessage());
			}

		}
	}
}