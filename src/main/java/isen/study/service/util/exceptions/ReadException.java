package isen.study.service.util.exceptions;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class ReadException extends Exception{
    public ReadException() {
        super();
    }

    public ReadException(String message, Throwable exception) {
        super(message, exception);
    }

    public ReadException(String message) {
        super(message);
    }

    public ReadException(Throwable exception) {
        super(exception);
    }
}
