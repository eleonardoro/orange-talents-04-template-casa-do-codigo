package br.com.zup.proposta.healthcheck.analisedecredito;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class AnaliseDeCreditoHealthCheck implements HealthIndicator {

	private final String NOME_DO_SERVICO = "Servico de Analise de Credito";
	private AnaliseDeCreditoHealthCheckFeignClient client;

	@Value("${url.analise.proposta}")
	private String URL;

	@Value("${path.analise.proposta.healtchek}")
	private String PATH;

	public AnaliseDeCreditoHealthCheck(AnaliseDeCreditoHealthCheckFeignClient client) {
		this.client = client;
	}

	@Override
	public Health health() {

		Map<String, Object> details = new HashMap<>();
		details.put("nome", NOME_DO_SERVICO);
		details.put("descrição", "Serviço de análise de crédito para nova proposta");
		details.put("url", URL);
		details.put("path", PATH);

		try {
			String status = client.verificaStatus();
			if (status.contains("UP")) {
				details.put("status", "UP");
				return Health.up().withDetails(details).build();
			}
		} catch (Exception e) {
			details.put("status", "DOWN");
			return Health.status(Status.DOWN).withDetails(details).build();
		}

		details.put("status", "DOWN");
		return Health.status(Status.DOWN).withDetails(details).build();
	}
}