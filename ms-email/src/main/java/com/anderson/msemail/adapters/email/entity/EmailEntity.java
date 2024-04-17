package com.anderson.msemail.adapters.email.entity;

import com.anderson.msemail.core.email.enums.StatusEmail;
import com.anderson.msemail.core.email.model.Email;
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
    private StatusEmail statusEmail;

    public EmailEntity(Email email) {
        this.id = email.getId();
        this.idUser = email.getIdUser();
        this.emailTo = email.getEmailTo();
        this.subject = email.getSubject();
        this.text = email.getText();
        this.sendDateEmail = email.getSendDateEmail();
        this.statusEmail = email.getStatusEmail();
    }
}
