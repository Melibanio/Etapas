package br.senac.tads.dsw.dadospessoais.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SenhasIguaisValidator.class)
public @interface SenhasIguais {

    String message() default "A senha e repetição devem ser iguais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
