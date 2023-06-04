package br.com.teste.capitulo.resource.user.dto;

import br.com.teste.capitulo.service.validator.UserUpdateValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@UserUpdateValid
public class UserUpdateDto implements Serializable {
    private static final long serialVersionUID = 1l;
    @NotBlank(message = "firstName cannot be null")
    private String firstName;
    @NotBlank(message = "lastName cannot be null")
    private String lastName;
    @Email(message="Email not Valid")
    private String email;

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
