package com.gma.assistance.gma.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
@RequestMapping("/email")
public class EmailController {

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

//    @Autowired
//    EmailService emailService;

    @Autowired
    JavaMailSender mailSender;

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    @GetMapping(value = "/simple-email/{user-email}")
    public @ResponseBody
    ResponseEntity sendSimpleEmail(@PathVariable("user-email") String email) {
        System.out.println(email);

        // Create a mail sender
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.mailtrap.io");
//        mailSender.setPort(2525);
//        mailSender.setUsername("465600c71e4838");
//        mailSender.setPassword("dc71bbca756631");

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("welmahask@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("New feedback from");
        mailMessage.setText("feedback.getFeedback()");

        // Send mail
        mailSender.send(mailMessage);


//        String[] to = {email, "elmahask@yahoo.com"}; // list of recipient email addresses
//        sendFromGMail("welmahask@gmail.com", "iwixzbkswscakeqj", to, "test", "test again");
//        try {
//            emailService.sendSimpleEmail("elmahask@yahoo.com", "Welcome", "This is a welcome email for your!!");
//        } catch (MailException mailException) {
//            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
//            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

//    @GetMapping(value = "/simple-order-email/{user-email}")
//    public @ResponseBody
//    ResponseEntity sendEmailAttachment(@PathVariable("user-email") String email) {
//
//        try {
//            emailService.sendEmailWithAttachment(email, "Order Confirmation", "Thanks for your recent order",
//                    "classpath:purchase_order.pdf");
//        } catch (MessagingException | FileNotFoundException mailException) {
//            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
//            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>("Please check your inbox for order confirmation", HttpStatus.OK);
//    }

}