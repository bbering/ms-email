package com.ms.ms_email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.ms_email.dtos.EmailRequestDTO;
import com.ms.ms_email.dtos.EmailResponseDTO;
import com.ms.ms_email.enums.StatusEmail;
import com.ms.ms_email.models.Email;
import com.ms.ms_email.repositories.EmailRepository;

import jakarta.transaction.Transactional;

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

    public Email toEntity(EmailRequestDTO emailDTO) {
        Email emailToConvert = new Email();
        emailToConvert.setContent(emailDTO.getMessageContent());
        emailToConvert.setEmailFrom(emailFrom);
        emailToConvert.setEmailSubject(emailDTO.getMessageSubject());
        emailToConvert.setEmailTo(emailDTO.getDestinationEmail());
        emailToConvert.setUserID(emailDTO.getRequesterUserId());

        return emailToConvert;
    }

    @Transactional
    public EmailResponseDTO sendEmail(EmailRequestDTO emailData) {
        Email emailToSave = toEntity(emailData);

        emailToSave.setSendingTime(LocalDateTime.now());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailToSave.getEmailTo());
        message.setSubject(emailToSave.getEmailSubject());
        message.setText(emailToSave.getContent());

        javaMailSender.send(message);

        emailToSave.setStatusEmail(StatusEmail.SENT);

        emailRepository.save(emailToSave);

        EmailResponseDTO emailResponse = new EmailResponseDTO();
        emailResponse.setDestinationEmail(emailToSave.getEmailTo());
        emailResponse.setMessageContent(emailToSave.getContent());
        emailResponse.setMessageSubject(emailToSave.getEmailSubject());

        return emailResponse;
    }

}
