package com.ms.ms_email.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedEventDTO {
    @JsonProperty("userId")
    private UUID id;
    private String name;
    private String email;
    private String emailTo;
}
