package com.anderson.msemail.core.email.model;

import com.anderson.msemail.core.email.dtos.EmailDTO;
import com.anderson.msemail.core.email.enums.StatusEmail;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Email {

    private UUID id;
    private UUID idUser;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private Integer statusEmail;

    public Email(EmailDTO dto) {
        this.idUser = dto.idUser();
        this.emailTo = dto.emailTo();
        this.subject = dto.subject();
        this.text = dto.text();
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getSendDateEmail() {
        return sendDateEmail;
    }

    public void setSendDateEmail(LocalDateTime sendDateEmail) {
        this.sendDateEmail = sendDateEmail;
    }

    public StatusEmail getStatusEmail() {
        return StatusEmail.valueOf(statusEmail);
    }

    public void setStatusEmail(StatusEmail statusEmail) {
        this.statusEmail = statusEmail.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
