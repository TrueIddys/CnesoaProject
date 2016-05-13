package com.cnesoa.exceptions;

/**
 * Created by Maxime on 29/04/2016.
 */
public class MissingAttributeException extends RuntimeException {

    public MissingAttributeException() {
    }

    public MissingAttributeException(String message) {
        super(message);
    }

    public MissingAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingAttributeException(Throwable cause) {
        super(cause);
    }

    public MissingAttributeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
