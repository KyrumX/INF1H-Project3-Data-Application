package Main;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Created by Selim on 13/4/2017.
 *
 * TODO: Intoscherm/loadingscherm maken
 */
public class Intro {
    public Intro(){

    }
    public void playit() {
        ImageView iv = new ImageView();
        Image image = new Image("file:Styling/about3.png");
        iv.setImage(image);

        FadeTransition ft = new FadeTransition();
        ft.setNode(iv);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(6);
        ft.setAutoReverse(true);

        ft.play();
    }
}
