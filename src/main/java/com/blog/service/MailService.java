package com.blog.service;

import com.blog.exception.SpringException;
import com.blog.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final MailContentBuilder mailContentBuilder;
    private final JavaMailSender javaMailSender;

    @Async
    void sendMail(NotificationEmail notificationEmail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(notificationEmail.getReceiver());
        simpleMailMessage.setTo("dev.karim10@gmail.com");
        simpleMailMessage.setSubject(notificationEmail.getSubject());
        simpleMailMessage.setText(notificationEmail.getContent());

        try {
            javaMailSender.send(simpleMailMessage);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new SpringException("Exception occurred when sending mail to " + notificationEmail.getReceiver());
        }
    }
}
