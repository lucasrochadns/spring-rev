package br.com.teste.capitulo.resource.category.dto;

import java.io.Serializable;

public class CategoryInput implements Serializable {
    private static final long serialVersionUID = 1l;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
