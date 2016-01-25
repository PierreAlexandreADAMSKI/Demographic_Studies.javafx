package isen.study.app.view;

import isen.study.app.DemographicStatsApp;
import isen.study.app.util.AppUtil;
import isen.study.app.util.BundleUtil;
import isen.study.app.view.listeners.ReaderChangeListener;
import isen.study.service.vcard.VCardRecorderService;
import isen.study.service.view.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.*;

import java.io.File;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class HomeViewController {

	private static final String CHOOSER_TITLE = "Choose a Vcard folder";
	private DirectoryChooser directoryChooser;
	private VCardRecorderService recorderService;
	@FXML
	Button launchButton;

	@FXML
	private void initialize() {
		launchButton.setDisable(true);
	}

	@FXML
	public void handleLaunchButton() {
		AppUtil.show(DemographicStatsApp.class.getResource("view/StatOverview.fxml"));
	}

	@FXML
	public void handleDirectoryChooserButton() {
		setup(BundleUtil.loadProperties("application"));
		handle();
	}

	public void setup(Properties properties) {
		directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle(CHOOSER_TITLE);
		directoryChooser.setInitialDirectory(new File(properties.getProperty("vcards.folder")));
	}

	public void handle() {
		Stage stage = new Stage(StageStyle.DECORATED);
		File directoryFolder = directoryChooser.showDialog(stage);
		if (directoryFolder != null) {
			final String currentFolderPath = directoryFolder.getAbsolutePath();
			//read from application properties and save to db from mDbService
			try {

				recorderService = new VCardRecorderService(DemographicStatsApp.getDbService(), currentFolderPath);

				URL standAloneUrl = DemographicStatsApp.class.getResource("view/ProgressBar.fxml");
				AppUtil.launchStandAlone(standAloneUrl, StageStyle.UTILITY);
				StageService.getStandAloneStage(standAloneUrl.getFile()).setResizable(false);

				recorderService.setOnSucceeded(event -> {
					//progressBarController.message.setText("Done !");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					launchButton.setDisable(false);
					StageService.getStandAloneStage(standAloneUrl.getFile()).close();
				});

				Thread recorder = new Thread(recorderService);
				recorder.setDaemon(true);
				//progressBarController.progressBar.progressProperty().bind(recorderService.progressProperty());
				//progressBarController.log.textProperty().bind(recorderService.messageProperty());
				recorder.start();

				DemographicStatsApp.setStatService(DemographicStatsApp.getDbService());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
