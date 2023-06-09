package br.com.teste.capitulo.resource.category;

import br.com.teste.capitulo.domain.Category;
import br.com.teste.capitulo.resource.category.dto.CategoryIO;
import br.com.teste.capitulo.resource.category.dto.CategoryInput;
import br.com.teste.capitulo.resource.category.dto.CategoryOutput;
import br.com.teste.capitulo.resource.utils.MapperUtil;
import br.com.teste.capitulo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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
    public Page<CategoryOutput> indexCategory(@PageableDefault(size = 12, sort = {"name"})Pageable pageable){
        return new PageImpl<CategoryOutput>(categoryService.getCategory(pageable).stream()
                .map(x -> new CategoryOutput(x.getId(), x.getName())).collect(Collectors.toList()));
    }

    @PostMapping({"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CategoryOutput createCategory(@RequestBody CategoryInput categoryInput){
        return mapperUtil.mapTo(categoryService
                .create(categoryIO.mapTo(categoryInput)), CategoryOutput.class);
    }

    @GetMapping({"/{id}/", "/{id}"})
    @ResponseBody
    public CategoryOutput findById(@PathVariable("id") Long id){
         return mapperUtil.mapTo(categoryService.findById(id), CategoryOutput.class);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        categoryService.delete(id);
    }

    @PutMapping({"/{id}/", "/{id}"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryOutput update(@PathVariable("id") Long id, @RequestBody CategoryInput categoryInput){
        return mapperUtil
                .mapTo(categoryService
                        .update(id, mapperUtil
                                .mapTo(categoryInput, Category.class)), CategoryOutput.class);
    }
}
