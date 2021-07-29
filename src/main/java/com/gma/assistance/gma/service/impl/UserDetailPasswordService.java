package com.gma.assistance.gma.service.impl;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.entity.UserPrincipal;
import com.gma.assistance.gma.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailPasswordService implements UserDetailsPasswordService {
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        Operator operator =
                operatorRepository.findByEmail(user.getUsername());
        operator.setPassword(passwordEncoder.encode(newPassword));
        return new UserPrincipal(operator);
    }

//    UserDetails toUserDetails(Operator userCredentials) {
//
//        return User.withUserDetails((UserDetails) userCredentials)
//                .password(userCredentials.getPassword())
//                .authorities(userCredentials.getRoles().toArray(String[]))
//                .build();
//    }
}
