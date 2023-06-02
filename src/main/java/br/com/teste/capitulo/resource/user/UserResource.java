package br.com.teste.capitulo.resource.user;

import br.com.teste.capitulo.domain.User;
import br.com.teste.capitulo.resource.user.dto.UserIO;
import br.com.teste.capitulo.resource.user.dto.UserInput;
import br.com.teste.capitulo.resource.user.dto.UserOutput;
import br.com.teste.capitulo.resource.user.dto.UserUpdateDto;
import br.com.teste.capitulo.resource.utils.MapperUtil;
import br.com.teste.capitulo.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value="/user")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private UserIO userIO;

    @GetMapping({"/", ""})
    @ResponseBody
    public Page<UserOutput> findByAll(@PageableDefault(size = 12, sort={"firstName"})Pageable pageable){
        return new PageImpl<UserOutput>(service.findAll(pageable).stream()
                .map(x -> new UserOutput(x)).collect(Collectors.toList()));
    }

    @GetMapping({"/{id}/", "/{id}"})
    @ResponseBody
    public UserOutput findById(@PathVariable("id") Long id){
        return mapperUtil.mapTo(service.findById(id), UserOutput.class);
    }

    @PostMapping({"/", ""})
    @ResponseBody
    public UserOutput create(@Valid @RequestBody UserInput userInput){
        return mapperUtil.mapTo(service.create(userIO.mapTo(userInput)), UserOutput.class);

    }

    @PutMapping({"/{id}/", "/{id}"})
    @ResponseBody
    public UserOutput update(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDto userUpdateDTO){
        return mapperUtil.mapTo(service.update(id, mapperUtil.mapTo(userUpdateDTO, User.class)), UserOutput.class);
    }
}
