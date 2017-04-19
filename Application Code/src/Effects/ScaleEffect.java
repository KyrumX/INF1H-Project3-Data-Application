package Effects;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;


/**
 * Created by Selim on 14/4/2017.
 */
public final class ScaleEffect {
    private static ScaleTransition st;
    public ScaleEffect(){
    }
    public static ScaleTransition getScaleEffect(Label label){
        st = new ScaleTransition();
        st.setNode(label);
        st.setFromX(1.0);
        st.setFromY(1.0);
        st.setToX(1.3);
        st.setToY(1.3);
        st.setCycleCount(-1);
        st.setDuration(new Duration(4000));
        st.setAutoReverse(true);
        return st;
    }
}
