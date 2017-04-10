package Graphs;

import javafx.scene.chart.PieChart;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaron on 7-4-2017.
 */
public class PieGraph {
    public PieChart chart;

    public PieGraph(HashMap<String, Double> chartData) {

        PieChart pieChart = new PieChart();

        for (Map.Entry<String, Double> entry : chartData.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));

        this.chart = pieChart;
        }
    }

    public PieChart getGraph() {
        return chart;
    }


}
