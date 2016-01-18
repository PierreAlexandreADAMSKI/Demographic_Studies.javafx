package isen.study.app.view;

import isen.study.app.DemographicStatsApp;
import isen.study.app.util.AppUtil;
import isen.study.app.util.BundleUtil;
import isen.study.service.vcard.VCardRecorderService;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Properties;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class HomeViewController {

	public static String mCurrentFolderPath;
	private DirectoryChooser directoryChooser = new DirectoryChooser();

	public String getCurrentFolderPath() {
		return mCurrentFolderPath;
	}

	public void setCurrentFolderPath(String currentFolderPath) {
		mCurrentFolderPath = currentFolderPath;
	}

	@FXML
	public void handleLaunchButton() {
		AppUtil.show(DemographicStatsApp.class.getResource("view/StatOverview.fxml"));
	}

	@FXML
	public void handleDirectoryChooserButton(){
		setup(BundleUtil.loadProperties("application"));
		handle();
	}

	public void setup(Properties properties){
		directoryChooser.setTitle("Choose a Vcard folder");
		directoryChooser.setInitialDirectory(new File(properties.getProperty("vcards.folder")));
	}

	public void handle() {
		Stage stage = new Stage(StageStyle.DECORATED);
		File directoryFolder = directoryChooser.showDialog(stage);
		if (directoryFolder != null) {
			setCurrentFolderPath(directoryFolder.getAbsolutePath());
			//read from application properties and save to db from mDbService
			try {
				VCardRecorderService.readAndSaveCards(getCurrentFolderPath(), DemographicStatsApp.getDbService());
				DemographicStatsApp.setStatService(DemographicStatsApp.getDbService());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
