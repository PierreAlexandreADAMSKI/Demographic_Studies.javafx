package isen.study.app.view.listeners;

import javafx.scene.control.ProgressBar;

/**
 * isen.study.app.view.listeners Created by Pierre-Alexandre Adamski on 20/01/2016.
 */
public interface ReaderChangeListener {
	void setProgress(double progress);
	ProgressBar getProgressBar();
}
