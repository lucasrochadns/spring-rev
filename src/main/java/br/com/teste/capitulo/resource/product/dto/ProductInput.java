package br.com.teste.capitulo.resource.product.dto;

import jdk.jfr.Category;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Set;

public class ProductInput implements Serializable {

    private static final long serialVersionUID = 1l;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;


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


}
