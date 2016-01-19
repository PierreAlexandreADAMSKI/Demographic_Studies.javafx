package isen.study.service.view;

import isen.study.app.DemographicStatsApp;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ViewService {

	public static <T> T getView(String id) {
		return getLoader(id).getRoot();
	}

	private static FXMLLoader getLoader(String id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(DemographicStatsApp.class.getResource("../../view/" + id + ".fxml"));
			loader.load();
			return loader;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
