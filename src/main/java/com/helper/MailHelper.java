package com.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailHelper{
    @Value("${mail.subject}")
    private String subject;

    @Autowired
    private JavaMailSender javaMailSender;
    public MailHelper(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
  public void sendMail(String receiverMail,String ticketNo) {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setTo(receiverMail);
      simpleMailMessage.setSubject(subject);
      simpleMailMessage.setText("Hii " +" \n your complaint is successfully register" + "\n your ticketNo:"
              + ticketNo);

                javaMailSender.send(simpleMailMessage);

  }
}
