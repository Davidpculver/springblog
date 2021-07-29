package com.codeup.springblog.services;


import com.codeup.springblog.models.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("mailService")
public class EmailService {

//    JavaMailSender = interface that sends the method
    @Autowired
    public JavaMailSender emailSender;

//    Value - sender properties
    @Value("${spring.mail.from}")
//    string from coming from @value and application.properties file
    private String from;

    public void prepareAndSend(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
// Have to have try catch to check for errors in setup
        try {
            this.emailSender.send(msg);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }

    }
}
