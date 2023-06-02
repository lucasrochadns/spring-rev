package br.com.teste.capitulo.service.validator;

import br.com.teste.capitulo.domain.User;
import br.com.teste.capitulo.repository.UserRepository;
import br.com.teste.capitulo.resource.exceptions.FieldMessage;
import br.com.teste.capitulo.resource.user.dto.UserUpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDto> {
    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;
    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateDto dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        @SuppressWarnings("unchecked")
        var uriVargs = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long userId = Long.parseLong(uriVargs.get("id"));

        User user = repository.findByEmail(dto.getEmail());
        if(user != null && userId != user.getId()){
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
