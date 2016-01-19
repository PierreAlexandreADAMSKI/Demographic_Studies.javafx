package isen.study.app.view;

import com.sun.javafx.scene.control.skin.ProgressBarSkin;
import isen.study.app.DemographicStatsApp;
import isen.study.app.util.AppUtil;
import isen.study.app.util.BundleUtil;
import isen.study.service.vcard.VCardRecorderService;
import isen.study.service.view.StageService;
import isen.study.service.view.Warning;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import sun.jvm.hotspot.ui.ProgressBarPanel;

import java.io.File;
import java.security.acl.Group;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class HomeViewController {

	private DirectoryChooser directoryChooser = new DirectoryChooser();

	@FXML
	public void handleLaunchButton() {
		if(VCardRecorderService.getProgress() == 1) {
			AppUtil.show(DemographicStatsApp.class.getResource("view/StatOverview.fxml"));
		}else if (VCardRecorderService.getProgress() == 0){
			StageService.getInstance().warning(Warning.NO_FILE);
		}else {
			StageService.getInstance().warning(Warning.WAIT);
		}
	}

	@FXML
	public void handleDirectoryChooserButton() {
		setup(BundleUtil.loadProperties("application"));
		handle();
	}

	public void setup(Properties properties) {
		this.directoryChooser.setTitle("Choose a Vcard folder");
		this.directoryChooser.setInitialDirectory(new File(properties.getProperty("vcards.folder")));
	}

	public void handle() {
		Stage stage = new Stage(StageStyle.DECORATED);
		File directoryFolder = this.directoryChooser.showDialog(stage);
		if (directoryFolder != null) {
			final String currentFolderPath = directoryFolder.getAbsolutePath();
			//read from application properties and save to db from mDbService
			try {
				loadingBar();
				Task<Void> dbTask = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						VCardRecorderService.readAndSaveCards(currentFolderPath, DemographicStatsApp.getDbService());
						DemographicStatsApp.setStatService(DemographicStatsApp.getDbService());
						return null;
					}
				};
				Thread thread = new Thread(dbTask);
				thread.setDaemon(true);
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void loadingBar() throws Exception {
		//creating Layout

		GridPane waitingPane = new GridPane();
		final ProgressBar progressBar = new ProgressBar();
		Text message = new Text("loading things...");
		waitingPane.addColumn(0, message, progressBar);
		message.setTextAlignment(TextAlignment.CENTER);

		Stage stage = new Stage(StageStyle.UTILITY);

		//Task for computing the Panels:
		Task<Void> progressBarTask = new Task<Void>() {
			//TODO bug when pushing start first
			//TODO bug as it comes
			@Override
			protected Void call() throws Exception {
				try {
					while (VCardRecorderService.getProgress() < 1) {
						progressBar.setProgress(VCardRecorderService.getProgress());
					}
					message.setText("Done!");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		progressBarTask.setOnSucceeded(event -> {
			stage.close();
		});

		Thread thread = new Thread(progressBarTask);
		thread.setDaemon(true);

		Scene scene = new Scene(waitingPane);
		stage.setScene(scene);
		stage.setResizable(false);


		thread.start();

		stage.show();
	}
}
