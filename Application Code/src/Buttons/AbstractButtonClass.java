package Buttons;

import javafx.scene.control.Button;

/**
 * Created by Selim on 6/4/2017.
 */
public abstract class AbstractButtonClass {
    private Button button;
    public AbstractButtonClass(){
        this.button = new Button();
    }
    public Button getButton(){
        return this.button;
    }
}
