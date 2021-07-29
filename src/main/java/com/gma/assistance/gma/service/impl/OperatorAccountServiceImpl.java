package com.gma.assistance.gma.service.impl;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.entity.SecureToken;
import com.gma.assistance.gma.exception.InvalidTokenException;
import com.gma.assistance.gma.exception.UnkownIdentifierException;
import com.gma.assistance.gma.model.ForgotPasswordEmailContext;
import com.gma.assistance.gma.repository.OperatorRepository;
import com.gma.assistance.gma.repository.SecureTokenRepository;
import com.gma.assistance.gma.service.IEmailService;
import com.gma.assistance.gma.service.IOperatorAccountService;
import com.gma.assistance.gma.service.IOperatorService;
import com.gma.assistance.gma.service.ISecureTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.util.Objects;

@Service
public class OperatorAccountServiceImpl implements IOperatorAccountService {

    @Autowired
    IOperatorService operatorService;
    @Autowired
    SecureTokenRepository secureTokenRepository;
    @Autowired
    private ISecureTokenService secureTokenService;
    @Value("${site.base.url.https}")
    private String baseURL;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void forgottenPassword(String userName) throws UnkownIdentifierException {
        Operator user = operatorService.findByEmail(userName);
        sendResetPasswordEmail(user);
    }

    @Override
    public void updatePassword(String password, String token) throws InvalidTokenException, UnkownIdentifierException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if (Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()) {
            throw new InvalidTokenException("Token is not valid");
        }
        Operator user = operatorRepository.findByEmail(secureToken.getOperator().getEmail());
        if (Objects.isNull(user)) {
            throw new UnkownIdentifierException("unable to find user for the token");
        }
        secureTokenService.removeToken(secureToken);
        user.setPassword(passwordEncoder.encode(password));
        operatorRepository.save(user);
    }


    protected void sendResetPasswordEmail(Operator user) {
        SecureToken secureToken = secureTokenService.saveSecureToken(user);
        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean loginDisabled(String username) {
        Operator user = operatorRepository.findByEmail(username);
        return user != null ? user.getEnabled() : false;
    }
}