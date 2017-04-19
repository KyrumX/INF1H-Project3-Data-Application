package Tools;

import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import static Main.Main.thewindow;

/**
 * Created by Selim on 19/4/2017.
 */
public class Icon extends HBox {
    public Icon() {
        Image image = new Image("/Styling/icon.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(20);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        this.getChildren().add(iv1);
    }
}
