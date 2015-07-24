package de.aaa.bankaccountactivityvisualizer.ui;

import java.io.File;
import java.io.IOException;

import de.aaa.bankaccountactivityvisualizer.ActivitiesGrouperByCategory;
import de.aaa.bankaccountactivityvisualizer.ActivitiesGrouperByMessage;
import de.aaa.bankaccountactivityvisualizer.TdAccountActivityCsvParser;
import de.aaa.bankaccountactivityvisualizer.domain.AccountActivity;
import de.aaa.bankaccountactivityvisualizer.domain.Grouping;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

public class MainWindow extends Application {

  public static void main(String[] args) throws IOException {
    launch(null);
  }

  @Override
  public void start(Stage stage) throws Exception {
    TdAccountActivityCsvParser parser = new TdAccountActivityCsvParser();
    AccountActivity accountActivity = parser.parseAccountActivity("C:\\workspace\\BankData\\Debit");
    Grouping groupedByMessage = new ActivitiesGrouperByCategory().transform(accountActivity.getAccountActivityItems());
    for (de.aaa.bankaccountactivityvisualizer.domain.Group item : groupedByMessage.getActivityGroups()) {
      System.out.println(item.getName() + " " + item.getTotalBookedAmount());
    }
    stage.setTitle("Account Visualizer");
    stage.setWidth(700);
    stage.setHeight(700);

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (de.aaa.bankaccountactivityvisualizer.domain.Group group : groupedByMessage.getActivityGroups()) {
      double bookedAmount = group.getTotalBookedAmount();
      if (bookedAmount < 0) {
        pieChartData.add(new PieChart.Data(group.getName(), -1 * bookedAmount));
      }
    }

    final PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Spendings");

    BorderPane borderPane = new BorderPane();
    TabPane tabPane = new TabPane();
    Tab pieChartTab = new Tab();
    pieChartTab.setText("Pie Chart");
    pieChartTab.setContent(chart);
    Tab monthlySpendingTab = new Tab();
    monthlySpendingTab.setText("Monthly Spending");
    tabPane.getTabs().add(pieChartTab);
    tabPane.getTabs().add(monthlySpendingTab);
    borderPane.setCenter(tabPane);
    Scene scene = new Scene(borderPane);
    stage.setScene(scene);
    stage.show();
  }

}
