package br.com.teste.capitulo.service.validator;

import br.com.teste.capitulo.domain.User;
import br.com.teste.capitulo.repository.UserRepository;
import br.com.teste.capitulo.resource.exceptions.FieldMessage;
import br.com.teste.capitulo.resource.user.dto.UserInput;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInput> {
    @Autowired
    private UserRepository repository;
    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInput dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        User user = repository.findByEmail(dto.getEmail());
        if(user != null){
           list.add(new FieldMessage("email", "Email já existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
