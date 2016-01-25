package isen.study.app.util;

import isen.study.service.view.StageService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class AppUtil {

	public static void show(URL url) { //create Exceptions!!!

		try {
			final FXMLLoader loader = new FXMLLoader(url);
			final AnchorPane anchorPane = loader.load();
			final Scene scene = new Scene(anchorPane);
			StageService.getInstance().getPrimaryStage().setScene(scene);
			StageService.getInstance().getPrimaryStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void launchStandAlone(URL url) throws IOException {
		final FXMLLoader loader = new FXMLLoader(url);
		final Scene scene = new Scene(loader.load());
		StageService.addStandAloneStage(new Stage(), url.getFile());
		StageService.getStandAloneStage(url.getFile()).setScene(scene);
		StageService.getStandAloneStage(url.getFile()).show();
	}

	public static void launchStandAlone(URL url, StageStyle stageStyle) throws IOException {
		final FXMLLoader loader = new FXMLLoader(url);
		final Scene scene = new Scene(loader.load());
		StageService.addStandAloneStage(new Stage(stageStyle), url.getFile());
		StageService.getStandAloneStage(url.getFile()).setScene(scene);
		StageService.getStandAloneStage(url.getFile()).show();
	}
}
