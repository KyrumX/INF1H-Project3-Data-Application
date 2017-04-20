package Tools;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by Selim on 19/4/2017.
 */
public class Icon extends HBox {
    public Icon() {
        Image image = new Image("/icon.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(20);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        this.getChildren().add(iv1);
    }
}
