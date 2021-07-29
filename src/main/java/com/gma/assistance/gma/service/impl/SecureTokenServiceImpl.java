package com.gma.assistance.gma.service.impl;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.entity.SecureToken;
import com.gma.assistance.gma.repository.SecureTokenRepository;
import com.gma.assistance.gma.service.ISecureTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SecureTokenServiceImpl implements ISecureTokenService {
    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom();
    private final Charset US_ASCII = Charset.forName("US-ASCII");

    @Value("${secure.token.validity}")
    private int tokenValidityInSeconds;// = 28800;

    @Autowired
    private SecureTokenRepository tokenRepository;

    //    @Transactional
//    @Override
    private SecureToken createToken() {
//        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
        SecureToken token = new SecureToken();
        token.setToken(UUID.randomUUID().toString());
//        token.setExpireAt(LocalDateTime.now().plusSeconds(tokenValidityInSeconds));
        token.setExpireAt(LocalDateTime.now().plusHours(48));
        return token;
    }

//    private SecureToken createeToken() {
//        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
//        SecureToken token = new SecureToken();
//        token.setToken(tokenValue);
//        token.setExpireAt(LocalDateTime.now().plusSeconds(tokenValidityInSeconds));
//        return token;
//    }

//    @Transactional
//    @Override
//    public void saveSecureToken(SecureToken token) {
//        SecureToken secureToken = tokenRepository.save(token);
//        System.out.println(secureToken.getId());
//        System.out.println(secureToken.getOperator().getId());
//        System.out.println(secureToken.getOperator().getEmail());
//    }

    @Transactional
    @Override
    public SecureToken saveSecureToken(Operator operator) {
        SecureToken secureToken = createToken();
        secureToken.setOperator(operator);
        secureToken = tokenRepository.save(secureToken);
        System.out.println(secureToken.getId());
        System.out.println(secureToken.getOperator().getId());
        System.out.println(secureToken.getOperator().getEmail());
        return secureToken;
    }

    @Override
    public SecureToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void removeByToken(String token) {
        tokenRepository.removeByToken(token);
    }

    @Override
    public void removeToken(SecureToken token) {
        tokenRepository.delete(token);
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }
}
