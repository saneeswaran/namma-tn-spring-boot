package com.app.tn.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.UNAUTHORIZED)
public class NotAuthorizedExceptions extends RuntimeException {

    public NotAuthorizedExceptions(String message) {
        super(message);
    }

}
