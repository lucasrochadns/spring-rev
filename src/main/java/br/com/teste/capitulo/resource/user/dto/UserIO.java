package br.com.teste.capitulo.resource.user.dto;

import br.com.teste.capitulo.domain.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component("userIO")
public class UserIO {

    private ModelMapper modelMapper;
    final Converter<UserInput, User> userConverter = new Converter<UserInput, User>() {

        @Override
        public User convert(MappingContext<UserInput, User> context) {
            UserInput userInput = context.getSource();
            User user = new User();
            user.setFirstName(userInput.getFirstName());
            user.setLastName(userInput.getLastName());
            user.setEmail(userInput.getEmail());
            user.setPassword(userInput.getPassword());
            return user;
        }
    };
    public UserIO(){
        modelMapper = new ModelMapper();
        modelMapper.addConverter(userConverter);
    }

    public User mapTo(UserInput userInput){
        return this.modelMapper.map(userInput, User.class);
    }
}


