package br.com.teste.capitulo.resource.product.dto;

import br.com.teste.capitulo.resource.category.dto.CategoryInput;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.HashSet;
import java.util.Set;

public class ProductInput implements Serializable {

    private static final long serialVersionUID = 1l;
    @Size(min = 5, max = 60, message = "Attribute Min 5 and Max 60 Character")
    @NotBlank(message="Name cannot be null")
    private String name;
    @NotBlank(message="Description cannot be null")
    private String description;
    @Positive(message="Price cannot be negative")
    private BigDecimal price;
    private String imgUrl;

    public Set<CategoryInput> categorySet = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<CategoryInput> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<CategoryInput> categorySet) {
        this.categorySet = categorySet;
    }
}
