package br.com.teste.capitulo.resource.category;

import br.com.teste.capitulo.domain.Category;
import br.com.teste.capitulo.resource.category.dto.CategoryIO;
import br.com.teste.capitulo.resource.category.dto.CategoryInput;
import br.com.teste.capitulo.resource.category.dto.CategoryOutput;
import br.com.teste.capitulo.resource.utils.MapperUtil;
import br.com.teste.capitulo.service.CategoryService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping(value="/category")

public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryIO categoryIO;

    @Autowired
    private MapperUtil mapperUtil;


    @GetMapping({"/", ""})
    @ResponseBody
    public List<?> indexCategory(){
        Type type = new TypeToken<List<CategoryOutput>>(){}.getType();
        return  mapperUtil.toList(categoryService.getCategory(), type);
    }

    @PostMapping({"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Object createCategory(@RequestBody CategoryInput categoryInput){
        Category category = categoryIO.mapTo(categoryInput);
        return categoryService.create(category);
    }
}
