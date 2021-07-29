package com.gma.assistance.gma.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private Operator operator;
    private List<Role> roles;

    public UserPrincipal() {
    }

    public UserPrincipal(Operator operator) {
        this.operator = operator;
    }

    public UserPrincipal(Operator operator, List<Role> roles) {
        this.operator = operator;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of roles (ROLE_name)
//        this.roles.forEach(r -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority(r.getAuthority());
//            System.out.println(r.getAuthority());
//            authorities.add(authority);
//        });

        this.operator.getRoles().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(r.getAuthority());
            System.out.println(r.getAuthority());
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return operator.getPassword();
    }

    @Override
    public String getUsername() {
        return operator.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return operator.getEnabled();
    }

    public String getFullName() {
        return operator.getFirstName() + " " + operator.getLastName();
    }
}
