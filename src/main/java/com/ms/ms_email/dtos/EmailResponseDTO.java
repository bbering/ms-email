package com.ms.ms_email.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResponseDTO {
    private String destinationEmail;
    private String messageSubject;
    private String messageContent;
}
