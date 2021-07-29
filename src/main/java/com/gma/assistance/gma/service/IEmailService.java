package com.gma.assistance.gma.service;

import com.gma.assistance.gma.model.EmailContext;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

public interface IEmailService {
//https://www.javadevjournal.com/spring-boot/send-email-using-spring/

    void sendSimpleEmail(String toAddress, String subject, String message);

    void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException;

    void sendMail(EmailContext email) throws MessagingException;
}
