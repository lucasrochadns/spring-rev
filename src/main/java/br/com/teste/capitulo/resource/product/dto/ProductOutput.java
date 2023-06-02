package br.com.teste.capitulo.resource.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import br.com.teste.capitulo.domain.Category;
import br.com.teste.capitulo.resource.category.dto.CategoryOutput;

import java.util.Set;

public class ProductOutput implements Serializable {

    private static final long serialVersionUID = 1l;

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
    private Set<CategoryOutput> categorySet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<CategoryOutput> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<CategoryOutput> categorySet) {
        this.categorySet = categorySet;
    }
}
