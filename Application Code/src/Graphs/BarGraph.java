package Graphs;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.util.HashMap;


/**
 * Created by aaron on 10-4-2017.
 */
public class BarGraph {
    public BarChart<String,Number> chart;

    public BarGraph(HashMap<String, Double> chartData, String deelGemeente) {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barGraph =
                new BarChart<String,Number>(xAxis,yAxis);
        barGraph.setTitle("Percentage autodiefstal");
        xAxis.setLabel("Deelgemeente: " + deelGemeente);
        yAxis.setLabel("Procent");

        for (String item : chartData.keySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(item);
            series.getData().add(new XYChart.Data("", chartData.get(item)));
            barGraph.getData().addAll(series);
        }
        barGraph.setLegendSide(Side.LEFT);
        barGraph.setId("test");
        chart = barGraph;

    }

    public BarChart getGraph() {
        return chart;
    }


}
