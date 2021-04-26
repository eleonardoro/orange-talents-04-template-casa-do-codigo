package br.com.zup.proposta.validacoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

	private String atributo;
	private Class<?> classe;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExisteId params) {
		atributo = params.atributo();
		classe = params.classe();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		Query query = manager.createQuery("select 1 from " + classe.getName() + " where " + atributo + " = :value");
		query.setParameter("value", value);

		List<?> list = query.getResultList();
		Assert.isTrue(list.size() <= 1, "aconteceu algo bizarro e você tem mais de um " + classe + " com o atributo "
				+ atributo + " com o valor = " + value);

		return !list.isEmpty();
	}
}