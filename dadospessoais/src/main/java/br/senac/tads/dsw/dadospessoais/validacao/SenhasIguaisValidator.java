package br.senac.tads.dsw.dadospessoais.validacao;

import org.springframework.stereotype.Component;

import br.senac.tads.dsw.dadospessoais.PessoaDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class SenhasIguaisValidator
        implements ConstraintValidator<SenhasIguais, PessoaDto> {

    private String mensagem;

    @Override
    public void initialize(SenhasIguais annotation) {
        this.mensagem = annotation.message();
    }

    @Override
    public boolean isValid(PessoaDto pessoa, ConstraintValidatorContext context) {
        boolean resultado = pessoa.getSenha() != null &&
                pessoa.getSenha().equals(pessoa.getSenhaRepeticao());
        if (!resultado) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(mensagem)
                    .addPropertyNode("senha").addConstraintViolation();
        }
        return resultado;
    }
}
