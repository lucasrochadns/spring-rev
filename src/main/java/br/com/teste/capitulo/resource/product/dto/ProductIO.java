package br.com.teste.capitulo.resource.product.dto;

import br.com.teste.capitulo.domain.Category;
import br.com.teste.capitulo.domain.Product;
import br.com.teste.capitulo.resource.category.dto.CategoryInput;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component("productIO")
public class ProductIO {

    private ModelMapper modelMapper;
    final Converter<ProductInput, Product> productConverter = new Converter<ProductInput, Product>() {

        @Override
        public Product convert(MappingContext<ProductInput, Product> context) {
            ProductInput productInput = context.getSource();
            Product product = new Product();
            product.setName(productInput.getName());
            product.setDescription(productInput.getDescription());
            product.setImgUrl(productInput.getImgUrl());
            product.setPrice(productInput.getPrice());
            return product;
        }
    };
    public ProductIO(){
        modelMapper = new ModelMapper();
        modelMapper.addConverter(productConverter);
    }

    public Product mapTo(ProductInput productInput){
        return this.modelMapper.map(productInput, Product.class);
    }
}


