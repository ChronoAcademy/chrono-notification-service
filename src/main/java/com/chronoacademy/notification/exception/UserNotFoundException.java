package com.chronoacademy.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)  // ← 404 pas 400
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("Utilisateur introuvable avec l'id : " + userId);
    }
}