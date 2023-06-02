package br.com.teste.capitulo.resource.category.dto;


import java.io.Serializable;

public class CategoryOutput implements Serializable {
    private static final long serialVersionUID = 1l;
    private Long id;
    private String name;

    public CategoryOutput() {
    }

    public CategoryOutput(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
