package com.gma.assistance.gma.service.impl;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.entity.Role;
import com.gma.assistance.gma.entity.UserPrincipal;
import com.gma.assistance.gma.repository.OperatorRepository;
import com.gma.assistance.gma.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Operator op = operatorRepository.findByEmail(email);
        if (op == null) {
            throw new UsernameNotFoundException(email);
        }
        List<Role> roles = op.getRoles();
        return new UserPrincipal(op, roles);
    }
}
