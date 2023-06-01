package br.com.teste.capitulo.resource.role;

import br.com.teste.capitulo.domain.Role;

import java.io.Serializable;

public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1l;
    private Long id;
    private String authority;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public RoleDto() {
    }

    public RoleDto(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
