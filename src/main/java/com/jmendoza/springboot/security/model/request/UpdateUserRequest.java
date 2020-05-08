package com.jmendoza.springboot.security.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class UpdateUserRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Set<String> role;

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

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
