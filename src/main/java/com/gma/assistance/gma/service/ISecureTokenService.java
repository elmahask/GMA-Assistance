package com.gma.assistance.gma.service;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.entity.SecureToken;

public interface ISecureTokenService {
//    SecureToken createToken();

//    void saveSecureToken(SecureToken token);

    SecureToken saveSecureToken(Operator operator);

    SecureToken findByToken(String token);

    void removeByToken(String token);

    void removeToken(SecureToken token);

}
