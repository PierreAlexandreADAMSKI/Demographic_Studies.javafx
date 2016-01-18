package isen.study.app;

import isen.study.app.util.AppUtil;
import isen.study.app.util.BundleUtil;
import isen.study.service.database.DBService;
import isen.study.service.database.exceptions.DBServiceConnectionException;
import isen.study.service.stat.StatService;
import isen.study.service.util.DBUtil;
import isen.study.service.view.StageService;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Properties;

public class DemographicStatsApp extends Application{

	public static DBService mDbService;
	public static StatService mStatService;


	public static DBService getDbService() {
		return mDbService;
	}

	public static void setDbService(Properties properties) throws DBServiceConnectionException {
		//clear db for now while testing. in the coming release user
		//must add as many cards as he wants before to clear them
		mDbService = new DBService(DBUtil.dbInit(properties));
		mDbService.clear();
	}

	public static StatService getStatService() {
		return mStatService;
	}

	public static void setStatService(DBService dbService) {
		//setup statservice on db
		mStatService = new StatService(dbService);
	}



	public static void main(String[] args) throws Exception {
		setDbService(BundleUtil.loadProperties("database"));
		launch(args); // for javaFx
	}

	@Override
	public void start(Stage primaryStage) throws Exception { //create exceptions!!!
		StageService.getInstance().setPrimaryStage(primaryStage);
		StageService.getInstance().getPrimaryStage().setTitle("Home");
		AppUtil.show(DemographicStatsApp.class.getResource("HomeView.fxml"));
	}
}
