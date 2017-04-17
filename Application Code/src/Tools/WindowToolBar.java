package Tools;

import Modifications.Draggable;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Created by Selim on 15/4/2017.
 */

public class WindowToolBar {
    private int height = 30;
    private ToolBar toolBar = new ToolBar();
    private HBox aligner = new HBox();
    public WindowToolBar(){
        this.toolBar.getStyleClass().add("tool");
        Draggable.setDraggable(this.toolBar);
        this.toolBar.setPrefHeight(height);
        this.toolBar.setMinHeight(height);
        this.toolBar.setMaxHeight(height);
        this.aligner.setHgrow(this.aligner, Priority.ALWAYS);
        this.toolBar.getItems().add(this.aligner);
        this.toolBar.getItems().add(new CloseButton(false));
    }

    public ToolBar getToolBar(){
        return this.toolBar;

    }

    public HBox getAligner(){
        return this.aligner;
    }

}
