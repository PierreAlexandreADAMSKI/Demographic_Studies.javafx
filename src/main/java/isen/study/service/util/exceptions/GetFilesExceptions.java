package isen.study.service.util.exceptions;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class GetFilesExceptions extends Exception{
	public GetFilesExceptions() {
		super();
	}

	public GetFilesExceptions(String message, Throwable exception) {
		super(message, exception);
	}

	public GetFilesExceptions(String message) {
		super(message);
	}

	public GetFilesExceptions(Throwable exception) {
		super(exception);
	}
}
