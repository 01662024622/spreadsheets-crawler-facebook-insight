package com.google.spreadsheet.facebook.services.impl;

import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {
    @Autowired
    public JavaMailSender emailSender;
    public void sendSimpleMessage(String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("vuthang.hustit@gmail.com");
        message.setCc("leethikimthu@gmail.com");
        message.setSubject("Theo dõi quy trình đẩy dữ liệu facebook marketing");
        message.setText(content);
        emailSender.send(message);
    }


}
