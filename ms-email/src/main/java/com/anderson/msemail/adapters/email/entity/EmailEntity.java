package com.anderson.msemail.adapters.email.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_emails")
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID idUser;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private Integer statusEmail;

    public EmailEntity() {

    }

    public EmailEntity(UUID id, UUID idUser, String emailTo, String subject, String text, LocalDateTime sendDateEmail, Integer statusEmail) {
        this.id = id;
        this.idUser = idUser;
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
        this.sendDateEmail = sendDateEmail;
        this.statusEmail = statusEmail;
    }
}
