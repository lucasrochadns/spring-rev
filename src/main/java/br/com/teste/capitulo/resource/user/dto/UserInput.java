package br.com.teste.capitulo.resource.user.dto;

import br.com.teste.capitulo.resource.role.RoleDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserInput implements Serializable {
    private static final long serialVersionUID = 1l;
    private String firstName;

    private String lastName;
    private String email;

    private String password;

    private Set<RoleDto> roles = new HashSet<>();

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
