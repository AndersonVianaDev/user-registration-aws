package com.anderson.msemail.core.email.dtos;

import java.util.UUID;

public record EmailDTO(UUID idUser, String emailTo, String subject, String text) {
}
