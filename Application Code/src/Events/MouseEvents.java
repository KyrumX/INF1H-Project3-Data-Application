package Events;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


/**
 * Created by aaron on 4-4-2017.
 */

public class MouseEvents implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
    }

    public static void getChoice(ChoiceBox<String> ChoiceYear) {
    }

    public static void getValuePieChart(PieChart chart, ObservableList<Node> children, boolean isProcent) {
        final Label numberCaption = new Label("     ");
        numberCaption.setStyle("-fx-font: 24 arial;");
        children.add(numberCaption);

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                    e -> {
                        numberCaption.setTranslateX(e.getSceneX());
                        numberCaption.setTranslateY(e.getSceneY());
                        int integerGarage = (int) data.getPieValue();
                        if (!isProcent) {
                            numberCaption.setText(String.valueOf("   " + integerGarage));
                        } else {
                            numberCaption.setText(String.valueOf("   " + integerGarage + "%"));
                        }
                        numberCaption.setVisible(true);
                    });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    e -> {
                        numberCaption.setVisible(false);
                    });
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> {
                        numberCaption.setVisible(true);
                    });
        }
    }

    public static void getValueBarChart(BarChart<String,Number> chart, ObservableList<Node> children, boolean isProcent) {
        final Label numberCaption = new Label("     ");
        numberCaption.setStyle("-fx-font: 24 arial;");
        children.add(numberCaption);

        for (XYChart.Series<String, Number> serie: chart.getData()){
            for (XYChart.Data<String, Number> data : serie.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                        e -> {
                            numberCaption.setTranslateX(e.getSceneX());
                            numberCaption.setTranslateY(e.getSceneY());
                            if (!isProcent) {
                                numberCaption.setText(String.valueOf("   " + data.getYValue()));
                            } else {
                                numberCaption.setText(String.valueOf("   " + data.getYValue() + "%"));
                            }
                            numberCaption.setVisible(true);
                        });
                data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                        e -> {
                            numberCaption.setVisible(false);
                        });
                data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                        e -> {
                            numberCaption.setVisible(true);
                        });
            }
        }
    }
}


