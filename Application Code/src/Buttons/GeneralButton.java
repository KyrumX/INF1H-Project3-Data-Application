package Buttons;
import Effects.Shadoweffect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by Selim on 6/4/2017.
 */

public class GeneralButton extends AbstractButtonClass {
    private Button button;
    public GeneralButton(int OffsetX,int OffsetY, String label, EventHandler<ActionEvent> x, boolean isExitButton){
        this.button = new Button();
        this.button.setMinSize(200, 50);
        this.button.setText(label);
        this.button.setOnAction(x);
        this.button.setEffect(new Shadoweffect(0.5).getShadow());
        this.button.setTranslateX(OffsetX);
        this.button.setTranslateY(OffsetY);

        if (isExitButton) {
            this.button.getStyleClass().add("exitButton");
        }

    }

    public void setSize(int a, int b){
        this.button.setMinSize(a, b);
    }

    public Button getButton(){
        return this.button;
    }

}
