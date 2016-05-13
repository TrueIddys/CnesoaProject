package com.cnesoa.exceptions;

/**
 * Created by Maxime on 10/05/2016.
 */
public class EleveAlreadyInBinomeException extends RuntimeException {

    public EleveAlreadyInBinomeException() {
    }

    public EleveAlreadyInBinomeException(String message) {
        super(message);
    }

    public EleveAlreadyInBinomeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EleveAlreadyInBinomeException(Throwable cause) {
        super(cause);
    }

    public EleveAlreadyInBinomeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
