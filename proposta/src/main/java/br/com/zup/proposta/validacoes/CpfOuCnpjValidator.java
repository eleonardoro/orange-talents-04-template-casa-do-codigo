package br.com.zup.proposta.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.proposta.util.ValidaCpfECnpj;

class CpfOuCnpjValidator implements ConstraintValidator<CpfOuCnpj, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ValidaCpfECnpj.validaCpf(value) || ValidaCpfECnpj.validaCpf(value);
	}

}