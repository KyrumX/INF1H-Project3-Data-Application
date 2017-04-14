package Effects;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;


/**
 * Created by Selim on 14/4/2017.
 */
public class FadeEffect {
    private FadeTransition fd;
    public FadeEffect(Label label){
        this.fd = new FadeTransition();
        this.fd.setNode(label);
        this.fd.setDuration(new Duration(5000));
        this.fd.setFromValue(0.3);
        this.fd.setToValue(1.0);
        this.fd.setCycleCount(5);
        this.fd.setRate(1.3);
        this.fd.setAutoReverse(true);
    }
    public FadeTransition getFadeTransition(){
        return this.fd;
    }
}
