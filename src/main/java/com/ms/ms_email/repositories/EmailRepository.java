package com.ms.ms_email.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.ms_email.models.Email;

public interface EmailRepository extends JpaRepository<Email, UUID>{
    
}
