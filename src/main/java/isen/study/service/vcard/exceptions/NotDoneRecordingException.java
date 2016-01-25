package isen.study.service.vcard.exceptions;

import isen.study.service.view.StageService;
import isen.study.service.view.exceptions.Warning;

/**
 * isen.study.service.vcard.exceptions Created by Pierre-Alexandre Adamski on 18/01/2016.
 */
public class NotDoneRecordingException extends Exception {
	public NotDoneRecordingException() {
		super();
		StageService.getInstance().warning(Warning.WAIT);
	}

	public NotDoneRecordingException(String message, Throwable exception) {
		super(message, exception);
		StageService.getInstance().warning(Warning.WAIT, message);
	}

	public NotDoneRecordingException(String message) {
		super(message);
		StageService.getInstance().warning(Warning.WAIT, message);
	}

	public NotDoneRecordingException(Throwable exception) {
		super(exception);
		StageService.getInstance().warning(Warning.WAIT);
	}
}
