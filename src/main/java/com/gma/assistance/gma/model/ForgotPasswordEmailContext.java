package com.gma.assistance.gma.model;

import com.gma.assistance.gma.entity.Operator;
import org.springframework.web.util.UriComponentsBuilder;

public class ForgotPasswordEmailContext extends EmailContext {

    private String token;

    @Override
    public <T> void init(T context){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        Operator customer = (Operator) context; // we pass the customer information
        put("firstName", customer.getFirstName());
        setTemplateLocation("emails/forgot-password");
        setSubject("Forgotten Password");
        setFrom("no-reply@gma-assistance.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/password/change").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}