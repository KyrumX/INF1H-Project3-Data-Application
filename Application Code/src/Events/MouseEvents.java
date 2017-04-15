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
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        children.add(caption);

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            int integerGarage = (int) data.getPieValue();
                            caption.setText(String.valueOf(integerGarage));
                            caption.setVisible(true);
                        }
                    });
        }
    }
}


