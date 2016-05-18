package com.cnesoa.exceptions;

/**
 * Created by Maxime on 17/05/2016.
 */
public class NotMatchingException extends RuntimeException {

    public NotMatchingException() {
    }

    public NotMatchingException(String message) {
        super(message);
    }

    public NotMatchingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMatchingException(Throwable cause) {
        super(cause);
    }

    public NotMatchingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
