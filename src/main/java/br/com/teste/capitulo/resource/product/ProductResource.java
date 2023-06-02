package br.com.teste.capitulo.resource.product;

import br.com.teste.capitulo.domain.Category;
import br.com.teste.capitulo.domain.Product;
import br.com.teste.capitulo.resource.category.dto.CategoryInput;
import br.com.teste.capitulo.resource.category.dto.CategoryOutput;
import br.com.teste.capitulo.resource.product.dto.ProductInput;
import br.com.teste.capitulo.resource.product.dto.ProductOutput;
import br.com.teste.capitulo.resource.utils.MapperUtil;
import br.com.teste.capitulo.service.ProductService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value="/product")
public class ProductResource {


    @Autowired
    private ProductService service;
    @Autowired
    private MapperUtil mapperUtil;

    @GetMapping({"/", ""})
    @ResponseBody
    public Page<ProductOutput> findAll(@PageableDefault(size = 12, sort = {"name"})Pageable pageable){
        Type type = new TypeToken<List<ProductOutput>>(){}.getType();
        return new PageImpl<>(mapperUtil.toList(service.index(pageable).stream().collect(Collectors.toList()), type));
    }

    @PostMapping({"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductOutput create(@RequestBody ProductInput productInput){
         return mapperUtil.mapTo(service.create(mapperUtil.mapTo(productInput, Product.class)), ProductOutput.class);
    }

    @GetMapping({"/{id}/", "/{id}"})
    @ResponseBody
    public ProductOutput findById(@PathVariable("id") Long id){
        return mapperUtil.mapTo(service.findById(id), ProductOutput.class);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        service.delete(id);
    }

    @PutMapping({"/{id}/", "/{id}"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ProductOutput update(@PathVariable("id") Long id, @RequestBody ProductInput productInput){
        return mapperUtil
                .mapTo(service.update(id, mapperUtil
                                .mapTo(productInput, Product.class)), ProductOutput.class);
    }
}
