package Effects;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;


/**
 * Created by Selim on 14/4/2017.
 */
public class FadeEffect {
    private static FadeTransition fd;
    public FadeEffect(){
    }
    public static FadeTransition getFadeTransition(Label label){
        fd = new FadeTransition();
        fd.setNode(label);
        fd.setDuration(new Duration(5000));
        fd.setFromValue(0.3);
        fd.setToValue(1.0);
        fd.setCycleCount(-1);
        fd.setRate(1.3);
        fd.setAutoReverse(true);
        return fd;
    }
}
