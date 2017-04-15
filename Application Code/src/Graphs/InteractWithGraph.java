package Graphs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InteractWithGraph extends Application {
    final static String austria = "Austria";
    final static String brazil = "Brazil";

    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        //now you can get the nodes.
        for (XYChart.Series<String,Number> serie: bc.getData()){
            for (XYChart.Data<String, Number> item: serie.getData()){
                item.getNode().setOnMousePressed((MouseEvent event) -> {
                    System.out.println("you clicked "+item.toString());
                });
            }
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
