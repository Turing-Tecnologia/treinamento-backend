package com.turingtecnologia.albatroz.backendalbatroz.exceptions.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotAcceptableException extends RuntimeException {

    private static final long serialVersionUID = 931982041759578115L;

    public ResourceNotAcceptableException(String message) {
        super(message);
    }
}
