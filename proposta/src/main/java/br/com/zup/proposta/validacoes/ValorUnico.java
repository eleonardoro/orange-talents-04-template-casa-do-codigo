package br.com.zup.proposta.validacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ValorUnicoValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

	String message() default "{br.com.zup.beanvalidation.valorunico}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String atributo();

	Class<?> classe();
}