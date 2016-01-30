package isen.study.app.view;

import isen.study.app.DemographicStatsApp;
import isen.study.app.util.AppUtil;
import isen.study.app.util.BundleUtil;
import isen.study.service.vcard.VCardRecorderService;
import isen.study.service.view.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
		AppUtil.showPrimary(DemographicStatsApp.class.getResource("view/StatOverview.fxml"));
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
				AppUtil.showStandAlone(standAloneUrl, StageStyle.UTILITY);
				StageService.getStandAloneStage(standAloneUrl.getFile()).setResizable(false);

				/**
				 * event listener for the progress of the stat
				 * when done should shut down progressBar window
				 */
				recorderService.setOnSucceeded(event -> {
					//TODO tried to get the control of the progressbar from this controller but still not working
					//TODO some day we'll get it working :)

					//Sleep was to change message "Loading..." on progressBar to "Done!" for 1 seconde
					//only possible by creating the progressBar stage from this controller for now
					//until a better solution is found
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//launchButton appears accessible
					launchButton.setDisable(false);
					//shut down progressBar
					StageService.getStandAloneStage(standAloneUrl.getFile()).close();
				});

				Thread recorder = new Thread(recorderService);
				recorder.setDaemon(true);
				recorder.start();

				DemographicStatsApp.setStatService(DemographicStatsApp.getDbService());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
