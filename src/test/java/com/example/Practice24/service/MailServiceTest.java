package com.example.Practice24.service;

import com.example.Practice24.models.Dog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private JavaMailSender sender;
    @Captor
    ArgumentCaptor<SimpleMailMessage> captor;

    @Test
    void sendMessage() {
        MailService mailService = new MailService(sender);
        mailService.sendMessage();

        verify(sender).send(captor.capture());
        SimpleMailMessage value = captor.getValue();

        assertEquals("hello", value.getText());
    }
}