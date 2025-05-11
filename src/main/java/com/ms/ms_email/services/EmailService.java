package com.ms.ms_email.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.ms_email.dtos.EmailRequestDTO;
import com.ms.ms_email.models.Email;
import com.ms.ms_email.repositories.EmailRepository;

@Service
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender javaMailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    public EmailRequestDTO toDTO(Email email) {
        EmailRequestDTO entityConverted = new EmailRequestDTO();
        entityConverted.setMessageContent(email.getContent());
        entityConverted.setMessageSubject(email.getEmailSubject());
        entityConverted.setRequesterUserId(email.getUserID());
        entityConverted.setDestinationEmail(email.getEmailTo());

        return entityConverted;
    }

    // metodo para enviar emails

}
