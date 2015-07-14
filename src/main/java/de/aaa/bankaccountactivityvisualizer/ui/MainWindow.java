package de.aaa.bankaccountactivityvisualizer.ui;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import de.aaa.bankaccountactivityvisualizer.ActivitiesGrouperByMessage;
import de.aaa.bankaccountactivityvisualizer.TdAccountActivityCsvParser;
import de.aaa.bankaccountactivityvisualizer.domain.AccountActivity;
import de.aaa.bankaccountactivityvisualizer.domain.AccountActivityItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class MainWindow extends Application {

	public static void main(String[] args) throws IOException {
		launch(null);
	}

	@Override
	public void start(Stage stage) throws Exception {
		TdAccountActivityCsvParser parser = new TdAccountActivityCsvParser();
		AccountActivity accountActivity = parser
				.parseAccountActivity(new File("C:\\Users\\malik.atalla\\Downloads\\accountactivity_2015-06.csv"));
		Collection<AccountActivityItem> groupedByMessage = new ActivitiesGrouperByMessage()
				.transform(accountActivity.getAccountActivityItems());
		for (AccountActivityItem item : groupedByMessage) {
			System.out.println(item.getMessage() + " " + item.getBookedAmount());
		}
		Scene scene = new Scene(new Group());
		stage.setTitle("Account Visualizer");
		stage.setWidth(1000);
		stage.setHeight(1000);

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (AccountActivityItem accountActivityItem : groupedByMessage) {
			double bookedAmount = accountActivityItem.getBookedAmount();
			if (bookedAmount < 0) {
				pieChartData.add(new PieChart.Data(accountActivityItem.getMessage(), -1 * bookedAmount));
			}
		}

		// ObservableList<PieChart.Data> pieChartData =
		// FXCollections.observableArrayList(
		// new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges",
		// 25), new PieChart.Data("Plums", 10),
		// new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Spendings");
		((Group) scene.getRoot()).getChildren().add(chart);
		stage.setScene(scene);
		stage.show();
	}

}
