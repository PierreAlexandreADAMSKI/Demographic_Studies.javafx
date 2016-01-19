package isen.study.service.view;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StageService {

	private StageService() {
	}

	private static class StageServiceHolder {
		private static final StageService INSTANCE = new StageService();
	}

	public static StageService getInstance() {
		return StageServiceHolder.INSTANCE;
	}

	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public void closePrimaryStage() {
		primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public static void closeAnyStage(Stage stage){
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void error(Error error){
	}

	public void warning(Warning warning, String description){
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initOwner(primaryStage);

		if (warning == Warning.WAIT){
			alert.setTitle("Please Wait...");
		}
		if (warning == Warning.NO_FILE){
			alert.setTitle("Please Open Files...");
		}
		alert.setContentText(description);
		alert.showAndWait();
	}

	public void warning(Warning warning){
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initOwner(primaryStage);

		if (warning == Warning.WAIT){
			alert.setTitle("Please Wait...");
		}
		if (warning == Warning.NO_FILE){
			alert.setTitle("Please Open Files...");
		}
		alert.showAndWait();
	}
}
