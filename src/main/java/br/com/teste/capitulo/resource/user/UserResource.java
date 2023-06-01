package br.com.teste.capitulo.resource.user;

import br.com.teste.capitulo.resource.user.dto.UserOutput;
import br.com.teste.capitulo.resource.utils.MapperUtil;
import br.com.teste.capitulo.service.UserService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;

@Controller
@RequestMapping(value="/user")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private MapperUtil mapperUtil;

    @GetMapping({"/", ""})
    @ResponseBody
    public Page<UserOutput> findByAll(@PageableDefault(size = 12, sort={"firstName"})Pageable pageable){
        Type type = new TypeToken<Page<UserOutput>>(){}.getType();
        return mapperUtil.toPage(service.findAll(pageable), type);
    }

    @GetMapping({"/{id}/", "/{id}"})
    @ResponseBody
    public UserOutput findById(@PathVariable("id") Long id){
        return mapperUtil.mapTo(service.findById(id), UserOutput.class);
    }

}
