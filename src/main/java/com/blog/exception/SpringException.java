package com.blog.exception;

import org.springframework.mail.MailException;

public class SpringException extends RuntimeException {
    public SpringException(String message) {
        super(message);
    }
}
