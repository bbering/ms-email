package com.ms.ms_email.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ms.ms_email.enums.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_emails")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    private UUID userID;

    private String emailFrom;
    private String emailTo;
    private String emailSubject;

    @Column(columnDefinition = "TEXT", name = "content")
    private String content;

    private LocalDateTime sendingTime;
    private StatusEmail statusEmail;

}
