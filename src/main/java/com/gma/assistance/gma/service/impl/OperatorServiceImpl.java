package com.gma.assistance.gma.service.impl;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.entity.SecureToken;
import com.gma.assistance.gma.exception.InvalidTokenException;
import com.gma.assistance.gma.exception.UnkownIdentifierException;
import com.gma.assistance.gma.exception.UserAlreadyExistException;
import com.gma.assistance.gma.model.AccountVerificationEmailContext;
import com.gma.assistance.gma.repository.OperatorRepository;
import com.gma.assistance.gma.repository.SecureTokenRepository;
import com.gma.assistance.gma.service.IEmailService;
import com.gma.assistance.gma.service.IOperatorService;
import com.gma.assistance.gma.service.ISecureTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.util.Objects;

@Service
public class OperatorServiceImpl implements IOperatorService {
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private ISecureTokenService secureTokenService;
    @Autowired
    private SecureTokenRepository secureTokenRepository;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Value("${site.base.url.https}")
    private String baseUrl;

    @Override
    public Operator findByEmail(String email) {
        return operatorRepository.findByEmail(email);
    }

    @Override
    public Operator findByUserName(String email) {
        return operatorRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public Operator registerOperator(Operator operator) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if (checkIfUserExist(operator.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        Operator userEntity = new Operator();
        userEntity.setId(operator.getId());
        userEntity.setFirstName(operator.getFirstName());
        userEntity.setLastName(operator.getLastName());
        userEntity.setEmail(operator.getEmail());
        userEntity.setEnabled(operator.getEnabled());
        userEntity.setVerified(operator.getVerified());
        String encodedPassword = passwordEncoder.encode(operator.getPassword());
        userEntity.setPassword(encodedPassword);
        userEntity.setConfirmPassword(encodedPassword);
        Operator op = operatorRepository.save(userEntity);
        sendRegistrationConfirmationEmail(op);
        return  op;
    }

    @Transactional
    @Override
    public void sendRegistrationConfirmationEmail(Operator operator) {
//        SecureToken token = secureTokenService.createToken();
//        token.setOperator(operator);
//        secureTokenRepository.save(token);

        SecureToken token = secureTokenService.saveSecureToken(operator);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(operator);
        emailContext.setToken(token.getToken());
        emailContext.buildVerificationUrl(baseUrl, token.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        Operator user = operatorRepository.findByEmail(secureToken.getOperator().getEmail());
        if(Objects.isNull(user)){
            return false;
        }
        user.setVerified(true);
        user.setEnabled(true);
        operatorRepository.save(user); // let's same user details

        // we don't need invalid password now
        secureTokenService.removeToken(secureToken);
        return true;
    }

    @Override
    public Operator getUserById(String email) throws UnkownIdentifierException {
        Operator user = operatorRepository.findByEmail(email);
        if (user == null) {
            // we will ignore in case account is not verified or account does not exists
            throw new UnkownIdentifierException("unable to find account or account is not active");
        }
        return user;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return findByEmail(email) != null ? true : false;
    }

}
