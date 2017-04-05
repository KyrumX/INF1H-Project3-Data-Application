package Effects;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Created by Selim on 5/4/2017.
 */
public class Shadoweffect {
    private double offset;
    private DropShadow shadow;
    public Shadoweffect(double offset){
        this.shadow = new DropShadow();
        this.offset = offset;
        this.shadow.setOffsetX(this.offset);
        this.shadow.setOffsetY(this.offset);
        this.shadow.setColor(Color.rgb(0, 0, 0, 0.51));
    }
    public void setShadowColor(Color a_color){
        this.shadow.setColor(a_color);
    }
    public DropShadow getShadow(){
        return this.shadow;
    }

}
