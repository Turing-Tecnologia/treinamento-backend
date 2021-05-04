package com.turingtecnologia.albatroz.backendalbatroz.exceptions.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7825961159179132958L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
