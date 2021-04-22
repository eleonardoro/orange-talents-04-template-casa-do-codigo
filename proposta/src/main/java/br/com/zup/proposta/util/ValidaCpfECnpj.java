package br.com.zup.proposta.util;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;

public class ValidaCpfECnpj {

	public static boolean validaCpf(String cpf) {

		try {
			new CPFValidator().assertValid(cpf);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean validaCnpj(String cnpj) {

		try {
			new CNPJValidator().assertValid(cnpj);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}