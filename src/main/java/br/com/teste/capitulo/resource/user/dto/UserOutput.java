package br.com.teste.capitulo.resource.user.dto;

import br.com.teste.capitulo.domain.User;
import br.com.teste.capitulo.resource.role.RoleDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserOutput implements Serializable {
    private static final long serialVersionUID = 1l;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleDto> roles = new HashSet<>();

    public UserOutput() {
    }

    public UserOutput(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getRoles().stream().map(r -> new RoleDto(r.getId(), r.getAuthority())).collect(Collectors.toSet()));
    }

    public UserOutput(Long id, String firstName, String lastName, String email, Set<RoleDto> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
