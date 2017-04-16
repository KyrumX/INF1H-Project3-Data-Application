package Effects;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;


/**
 * Created by Selim on 14/4/2017.
 */
public final class ScaleEffect {
    private ScaleTransition st;
    public ScaleEffect(Label label){
        this.st = new ScaleTransition();
        this.st.setNode(label);
        this.st.setFromX(1.0);
        this.st.setFromY(1.0);
        this.st.setToX(1.3);
        this.st.setToY(1.3);
        this.st.setCycleCount(-1);
        this.st.setDuration(new Duration(4000));
        this.st.setAutoReverse(true);
    }
    public ScaleTransition getScaleEffect(){
        return this.st;
    }
}
