package com.gma.assistance.gma.listener;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.entity.SecureToken;
import com.gma.assistance.gma.model.ForgotPasswordEmailContext;
import com.gma.assistance.gma.service.IEmailService;
import com.gma.assistance.gma.service.ISecureTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    //https://github.com/elmahask/spring-security-registration/blob/master/src/main/java/com/baeldung/registration/listener/RegistrationListener.java

    @Autowired
    private ISecureTokenService secureTokenService;

    @Autowired
    private IEmailService emailService;

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
//        final Operator user = event.getUser();
        sendResetPasswordEmail(event);
    }
    protected void sendResetPasswordEmail(final OnRegistrationCompleteEvent event) {
        SecureToken secureToken = secureTokenService.saveSecureToken(event.getUser());
        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(event.getUser());
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(event.getAppUrl(), secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    protected void sendResetPasswordEmail(final OnRegistrationCompleteEvent event, Operator user) {
        SecureToken secureToken = secureTokenService.saveSecureToken(user);
        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(event.getAppUrl(), secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}