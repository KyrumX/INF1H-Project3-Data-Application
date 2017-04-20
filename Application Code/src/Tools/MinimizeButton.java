package Tools;

import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import static Main.Main.thewindow;

/**
 * Created by Selim on 17/4/2017.
 */
public class MinimizeButton extends HBox {
    public MinimizeButton(boolean isShifted) {
        Image image = new Image("/minimize.png");
        ImageView iv1 = new ImageView();
        iv1.setId("closebutton");
        iv1.setImage(image);
        iv1.setFitWidth(17);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);

        iv1.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {

            iv1.setEffect(colorAdjust);

        });
        iv1.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            iv1.setEffect(null);
        });

        iv1.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                thewindow.setIconified(true);
            }
        });

        if (isShifted){
            this.setTranslateY(4);
        }

        this.getChildren().add(iv1);
    }
}
