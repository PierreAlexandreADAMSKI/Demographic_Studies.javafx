package isen.study.app.view;

import isen.study.app.DemographicStatsApp;
import isen.study.data.stat.AverageAgeByState;
import isen.study.data.stat.CommonLastnamesByState;
import isen.study.data.stat.MostCommonBloodType;
import isen.study.data.stat.interfaces.Stat;
import isen.study.service.database.exceptions.DBServiceConnectionException;
import isen.study.service.stat.StatService;
import isen.study.service.view.StageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.*;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class StatsOverviewController implements EventHandler<ActionEvent> {

	private static final String MENU_TEXT = "Statistics";
	private static final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	@FXML
	private MenuButton menuButton;
	@FXML
	private final MenuItem averageAgeByState = new MenuItem("Average age by state");
	private final MenuItem commonLastnameByState = new MenuItem("Common last names by state");
	private final MenuItem mostCommonBloodType = new MenuItem("Most common blood type");


	@FXML
	private void initialize() throws DBServiceConnectionException {
		StatService statService = DemographicStatsApp.getStatService();
		//list of existing statistics
		java.util.List<Stat> stats = new ArrayList<>();
		stats.add(new AverageAgeByState());
		stats.add(new CommonLastnamesByState());
		stats.add(new MostCommonBloodType());
		statService.printStats(stats);

		ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
		menuItems.addAll(averageAgeByState, commonLastnameByState, mostCommonBloodType);
		this.menuButton.setText(MENU_TEXT);
		this.menuButton.getItems().setAll(averageAgeByState, commonLastnameByState, mostCommonBloodType);
		for (MenuItem item : menuItems) {
			item.setOnAction(this);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == averageAgeByState) {
			onClickAverageAgeByState();
		}
		if (event.getTarget() == commonLastnameByState) {
			onClickCommonLastnamesByState();
		}
		if (event.getTarget() == mostCommonBloodType) {
			onClickMostCommonBloodType();
		}
	}

	private BarChart<String, Number> newCustomBarChart(String title, String labelX, String labelY) {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		xAxis.setLabel(labelX);
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel(labelY);
		barChart.setTitle(title);
		barChart.setPrefWidth(WIDTH);
		barChart.setPrefHeight(HEIGHT);
		barChart.setAnimated(false);
		return barChart;
	}

	private void onClickAverageAgeByState() {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		BarChart<String, Number> barChart = newCustomBarChart(AverageAgeByState.TITLE, "States", "Average Age");
		Set<Map.Entry<String, Float>> set = AverageAgeByState.averageAgeByState.entrySet();
		for (Map.Entry<String, Float> entry : set) {
			final String state = entry.getKey();
			final Float averageAge = entry.getValue();

			series.getData().add(new XYChart.Data<>(state, averageAge));
		}
		Scene scene = new Scene(barChart, WIDTH, HEIGHT);
		barChart.getData().add(series);
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setScene(scene);
		stage.show();
	}

	private void onClickCommonLastnamesByState() {
		Collection<XYChart.Series<String, Number>> series = new ArrayList<>();
		BarChart<String, Number> barChart = newCustomBarChart(CommonLastnamesByState.TITLE, "names", "total");
		try {
			Set<Map.Entry<String, Map<String, Integer>>> statesSet = CommonLastnamesByState.commonLastnamesByState.entrySet();
			for (Map.Entry<String, Map<String, Integer>> entryStates : statesSet) {
				Set<Map.Entry<String, Integer>> subSet = entryStates.getValue().entrySet();
				XYChart.Series<String, Number> dataSerie = new XYChart.Series<>();
				for (Map.Entry<String, Integer> entry : subSet) {
					final String state = entry.getKey();
					final Integer total = entry.getValue();
					dataSerie.setName(entryStates.getKey());
					dataSerie.getData().add(new XYChart.Data<>(state, total));
				}
				series.add(dataSerie);
			}
			Scene scene = new Scene(barChart, WIDTH, HEIGHT);
			barChart.getData().addAll(series);
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			StageService.getInstance().errorStage();
		}
	}

	private void onClickMostCommonBloodType() {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		BarChart<String, Number> barChart = newCustomBarChart(MostCommonBloodType.TITLE, "blood types", "total");
		Set<Map.Entry<String, Integer>> statesSet = MostCommonBloodType.mostCommonBloudType.entrySet();
		for (Map.Entry<String, Integer> entry : statesSet) {
			String bloodType = entry.getKey();
			Integer total = entry.getValue();
			series.getData().add(new XYChart.Data<>(bloodType, total));
		}
		Scene scene = new Scene(barChart, WIDTH, HEIGHT);
		barChart.getData().add(series);
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setScene(scene);
		stage.show();
	}
}