package com.anderson.msuser.core.user.dtos;

import java.util.UUID;

public record EmailDTO(UUID idUser, String emailTo, String subject, String text) {
}
