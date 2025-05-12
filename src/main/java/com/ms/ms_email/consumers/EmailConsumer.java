package com.ms.ms_email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.ms_email.dtos.EmailRequestDTO;
import com.ms.ms_email.dtos.UserCreatedEventDTO;
import com.ms.ms_email.services.EmailService;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload UserCreatedEventDTO event) {
        EmailRequestDTO emailRequest = new EmailRequestDTO();
        emailRequest.setRequesterUserId(event.getId());
        emailRequest.setDestinationEmail(event.getEmail());
        emailRequest.setMessageSubject("Bem vindo: " + event.getName());
        emailRequest.setMessageContent("Olá! " + event.getName() + ", o seu cadastro foi concluído com sucesso!");

        emailService.sendEmail(emailRequest);
    }

}
