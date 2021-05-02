package com.example.Practice24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender sender;
    @Value("${email.address}")
    private String emailAddress;

    @Autowired
    public MailService(JavaMailSender sender) {
        this.sender = sender;
    }

    @Async
    public void sendMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailAddress);
        message.setTo("");
        message.setSubject("");
        message.setText("hello");
        sender.send(message);
    }

}
