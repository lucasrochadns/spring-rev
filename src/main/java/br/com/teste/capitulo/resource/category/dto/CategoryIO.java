package br.com.teste.capitulo.resource.category.dto;

import br.com.teste.capitulo.domain.Category;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component("categoryIO")
public class CategoryIO {
    private ModelMapper modelMapper;
    final Converter<CategoryInput, Category> categoryConverter = new Converter<CategoryInput, Category>() {

        @Override
        public Category convert(MappingContext<CategoryInput, Category> context) {
            CategoryInput categoryInput = context.getSource();
            Category category = new Category();
            category.setName(categoryInput.getName());
            return category;
        }
    };
    public CategoryIO(){
        modelMapper = new ModelMapper();
        modelMapper.addConverter(categoryConverter);
    }

    public Category mapTo(CategoryInput categoryInput){
        return this.modelMapper.map(categoryInput, Category.class);
    }
}
