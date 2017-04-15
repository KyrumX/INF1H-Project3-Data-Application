package Events;

import Database.ConnectDatabase;
import Graphs.BarGraph;
import Graphs.PieGraph;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Main.Main;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * Created by aaron on 4-4-2017.
 */

public class MouseEvents implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
    }
    public static void getChoice(ChoiceBox<String> ChoiceYear){
    }
    public static void getValueChart(PieChart chart, ObservableList<Node> children) {
        final Label numberCaption = new Label("");
        numberCaption.setStyle("-fx-font: 24 arial;");
        children.add(numberCaption);

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                            numberCaption.setTranslateX(e.getSceneX());
                            numberCaption.setTranslateY(e.getSceneY());
                            int integerGarage = (int) data.getPieValue();
                            numberCaption.setText(String.valueOf(integerGarage));
                            numberCaption.setVisible(true);
                    });
        }
    }
}


