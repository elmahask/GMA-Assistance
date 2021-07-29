package com.gma.assistance.gma.dto;

import com.gma.assistance.gma.entity.Role;
import com.gma.assistance.gma.validation.FieldMatch;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class OperatorDto implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true, nullable = false)
//    private Integer id;

    //    @NotEmpty(message = "{registration.validation.firstName}")
    @NotNull
    @Size(max = 20, min = 3, message = "first name must contain at least 3 and max 20")
    @NotEmpty(message = "{registration.validation.firstName}")
    private String firstName;
    @NotNull
    @NotEmpty(message = "{registration.validation.lastName}")
    @Size(max = 30, min = 3, message = "last name must contain at least 3 and max 20")
    private String lastName;
    @NotNull
    @NotEmpty(message = "{registration.validation.password}")
    @Size(min = 3, message = "password name must be at least 6 and max 20")
    private String password;
    @NotNull
    @NotEmpty(message = "{registration.validation.password}")
    @Size(max = 20, min = 3, message = "password name must be at least 6 and max 20")
    private String confirmPassword;
    @NotNull
    @NotEmpty(message = "{registration.validation.email}")
    @Email
    private String email;

    public OperatorDto() {
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Set<SecureToken> getTokens() {
//        return tokens;
//    }
//
//    public void setTokens(Set<SecureToken> tokens) {
//        this.tokens = tokens;
//    }

    @Override
    public String toString() {
        return "Operator{" +
//                "id=" + id + '\'' +
                "firstName=" + firstName + '\'' +
                "lastName=" + lastName + '\'' +
                "password=" + password + '\'' +
                "email=" + email + '\'' +
//                "enabled=" + enabled + '\'' +
                '}';
    }
}
