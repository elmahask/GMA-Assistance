package com.gma.assistance.gma.entity;

import com.gma.assistance.gma.validation.FieldMatch;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "operator")
//@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    //    @NotEmpty(message = "{registration.validation.firstName}")
    @Size(max = 20, min = 3, message = "first name must contain at least 3 and max 20")
    @NotEmpty(message = "{registration.validation.firstName}")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "{registration.validation.lastName}")
    @Size(max = 30, min = 3, message = "last name must contain at least 3 and max 20")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "{registration.validation.password}")
    @Size(min = 3, message = "password name must be at least 6 and max 20")
    @Column(name = "password", nullable = false)
    private String password;

    //    @NotEmpty(message = "{registration.validation.password}")
//    @Size(max = 20, min = 3, message = "password name must be at least 6 and max 20")
    @Transient
    private String confirmPassword;

    @NotEmpty(message = "{registration.validation.email}")
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "enabled", nullable = true, columnDefinition = "tinyint(1) default false")
    private Boolean enabled = false;

    @Column(name = "verified", nullable = true, columnDefinition = "tinyint(1) default false")
    private Boolean verified = false;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "operator_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

//    @OneToMany(mappedBy = "operator")
//    private Set<SecureToken> tokens;

    public Operator() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
                "id=" + id + '\'' +
                "firstName=" + firstName + '\'' +
                "lastName=" + lastName + '\'' +
                "password=" + password + '\'' +
                "email=" + email + '\'' +
                "enabled=" + enabled + '\'' +
                '}';
    }
}
