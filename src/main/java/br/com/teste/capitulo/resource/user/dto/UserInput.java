package br.com.teste.capitulo.resource.user.dto;

import br.com.teste.capitulo.resource.role.RoleDto;
import br.com.teste.capitulo.service.validator.UserInsertValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@UserInsertValid
public class UserInput implements Serializable {
    private static final long serialVersionUID = 1l;
    @NotBlank(message = "firstName cannot be null")
    private String firstName;
    @NotBlank(message = "lastName cannot be null")
    private String lastName;
    @Email(message="Email not Valid")
    private String email;

    public UserInput() {
    }

    public UserInput(String firstName, String lastName, String email, String password, Set<RoleDto> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

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
