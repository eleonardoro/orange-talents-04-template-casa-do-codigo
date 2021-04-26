package br.com.zup.proposta.validacoes;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ExisteIdValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ExisteId {

	String message() default "{br.com.zup.beanvalidation.existeid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String atributo();

	Class<?> classe();
}