package com.cnesoa.exceptions;

/**
 * Created by Maxime on 10/05/2016.
 */
public class WrongDateException extends RuntimeException {

    public WrongDateException() {
    }

    public WrongDateException(String message) {
        super(message);
    }

    public WrongDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDateException(Throwable cause) {
        super(cause);
    }

    public WrongDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
