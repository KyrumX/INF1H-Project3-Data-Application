package Modifications;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Main.Main.thewindow;
import static Main.Main.window;

/**
 * Created by Selim on 14/4/2017.
 */
public class Draggable {
    public static double xOffset = 0;
    public static double yOffset = 0;
    public Draggable(){

    }

    public static void setDraggable(Node x){
        x.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        x.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                thewindow.setX(event.getScreenX() - xOffset);
                thewindow.setY(event.getScreenY() - yOffset);
            }
        });

    }
}
