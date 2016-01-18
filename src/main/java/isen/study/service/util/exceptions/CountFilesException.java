package isen.study.service.util.exceptions;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class CountFilesException extends Exception {
	public CountFilesException() {
		super();
	}

	public CountFilesException(String message, Throwable exception) {
		super(message, exception);
	}

	public CountFilesException(String message) {
		super(message);
	}

	public CountFilesException(Throwable exception) {
		super(exception);
	}
}
