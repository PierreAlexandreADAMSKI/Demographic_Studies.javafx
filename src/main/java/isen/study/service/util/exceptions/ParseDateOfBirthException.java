package isen.study.service.util.exceptions;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class ParseDateOfBirthException extends Exception{
    public ParseDateOfBirthException() {
        super();
    }

    public ParseDateOfBirthException(String message, Throwable exception) {
        super(message, exception);
    }

    public ParseDateOfBirthException(String message) {
        super(message);
    }

    public ParseDateOfBirthException(Throwable exception) {
        super(exception);
    }
}
