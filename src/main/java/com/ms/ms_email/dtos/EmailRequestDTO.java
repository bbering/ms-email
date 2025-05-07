package com.ms.ms_email.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    private UUID requesterUserId;

    @NotBlank(message = "O e-mail de destino é obrigatório.")
    @Email(message = "E-mail de destino inválido.")
    private String destinationEmail;

    @NotBlank(message = "O assunto do e-mail é obrigatório.")
    private String messageSubject;

    @NotBlank(message = "O conteúdo do e-mail é obrigatório.")
    private String messageContent;
}