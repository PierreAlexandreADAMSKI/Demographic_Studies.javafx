package isen.study.service.util.exceptions;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class GetAllLinesException extends Exception {
	public GetAllLinesException() {
		super();
	}

	public GetAllLinesException(String message, Throwable exception) {
		super(message, exception);
	}

	public GetAllLinesException(String message) {
		super(message);
	}

	public GetAllLinesException(Throwable exception) {
		super(exception);
	}
}
