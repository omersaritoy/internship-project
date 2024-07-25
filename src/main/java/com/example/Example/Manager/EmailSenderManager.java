package com.example.Example.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderManager {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderManager(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String userName, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Verification Code");
        message.setText("Hello " + userName + ", your verification code is: " + verificationCode);
        mailSender.send(message);
    }


}
