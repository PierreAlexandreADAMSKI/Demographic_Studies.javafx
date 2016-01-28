package isen.study.app.view;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * isen.study.app.view Created by Pierre-Alexandre Adamski on 19/01/2016.
 */
public class ProgressBarController {
	@FXML
	private Text message;
	@FXML
	private ProgressBar progressBar;

	@FXML
	private void initialize(){
		message.setText("Loading...");
		progressBar.setProgress(-1);
	}

}
