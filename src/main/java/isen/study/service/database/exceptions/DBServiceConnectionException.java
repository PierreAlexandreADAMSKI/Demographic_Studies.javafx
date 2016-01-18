package isen.study.service.database.exceptions;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class DBServiceConnectionException extends Exception {
	public DBServiceConnectionException() {
		super();
	}

	public DBServiceConnectionException(String message, Throwable exception) {
		super(message, exception);
	}

	public DBServiceConnectionException(String message) {
		super(message);
	}

	public DBServiceConnectionException(Throwable exception) {
		super(exception);
	}
}
