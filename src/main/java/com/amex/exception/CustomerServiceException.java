package com.amex.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerServiceException extends RuntimeException {
    private String message;

    public CustomerServiceException(String message) {
        super(message);
    }
}
