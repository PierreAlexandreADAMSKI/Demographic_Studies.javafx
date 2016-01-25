package isen.study.app.view;
import isen.study.app.view.listeners.ReaderChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * isen.study.app.view Created by Pierre-Alexandre Adamski on 19/01/2016.
 */
public class ProgressBarController implements ReaderChangeListener{
	@FXML
	private Text message;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TextArea log;

	@FXML
	private void initialize(){
	}

	@Override
	public void setProgress(double progress) {
		progressBar.setProgress(progress);
	}

	@Override
	public ProgressBar getProgressBar() {
		return this.progressBar;
	}
}
