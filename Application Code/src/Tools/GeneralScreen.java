package Tools;

import Modifications.Draggable;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * Created by Selim on 18/4/2017.
 */
public class GeneralScreen {
    private BorderPane bPane = new BorderPane();
    private StackPane sPane = new StackPane();
    private ToolBar TBar = new ToolBar();
    public GeneralScreen(){
        this.sPane = new StackPane();
        Draggable.setDraggable(this.sPane);
        this.TBar = new ToolBar();
        Draggable.setDraggable(this.TBar);
        this.TBar.getStyleClass().add("tool");
    }
    public BorderPane getbPane(){
        return this.bPane;
    }
    public StackPane getsPane(){
        return this.sPane;
    }
    public ToolBar getTBar(){
        return this.TBar;
    }
    public Scene setUpScene(BorderPane a, StackPane b, ToolBar c){
        a.setCenter(b);
        a.setTop(c);
        return new Scene(a, 720, 540);
    }

}
