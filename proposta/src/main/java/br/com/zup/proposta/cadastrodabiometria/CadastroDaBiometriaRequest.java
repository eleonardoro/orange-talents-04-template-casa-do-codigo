package br.com.zup.proposta.cadastrodabiometria;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.proposta.cartao.Cartao;

public class CadastroDaBiometriaRequest {

	@NotEmpty
	private String biometria;

	@JsonCreator
	public CadastroDaBiometriaRequest(@NotEmpty @JsonProperty("biometria") String biometria) {
		this.biometria = biometria;
	}

	Biometria converterParaBiometria(Cartao cartao) {
		return new Biometria(biometria, cartao);
	}

}