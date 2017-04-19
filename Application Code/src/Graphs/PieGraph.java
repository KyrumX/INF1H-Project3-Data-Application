package Graphs;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaron on 7-4-2017.
 */
public class PieGraph {
    public PieChart chart;

    public PieGraph(HashMap<String, Double> chartData, String titel) {

        PieChart pieChart = new PieChart();
        pieChart.setTitle(titel);

        //Loop door de HashMap en vul de Graph met data;
        for (Map.Entry<String, Double> entry : chartData.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));

        }
        pieChart.setLabelLineLength(10);
        pieChart.setLegendSide(Side.LEFT);
        this.chart = pieChart;
    }

    public PieChart getGraph() {
        return chart;
    }


}
