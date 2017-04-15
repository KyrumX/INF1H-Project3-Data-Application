package Main;

import Database.ConnectDatabase;
import Buttons.AbstractButtonClass;
import Buttons.GeneralButton;
import Effects.FadeEffect;
import Effects.ScaleEffect;
import Effects.Shadoweffect;
import Graphs.BarGraph;
import Graphs.PieGraph;
import Modifications.Draggable;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Aaron on 2-4-2017.
 * .gitignore has been instructed to ignore certain files.
 * This file will act as the Main file, we will call all code from this file.
 */

public class Main extends Application {
    public static double versionNumber = 1.0;
    public static AbstractButtonClass exitButton, startButton, backButton;
    public static Stage window;
    public static Scene mainScene, garageScene, chooseScene, cartheftScene;
    public static boolean menuState;
    public static BorderPane mainScreen, cartheftScreen;
    public static Stage thewindow;
    public Label label1;
    public FadeTransition ft;

    //Bepalen van de State van het programma;
    private enum StateL{
        MENU
    }

    private StateL State = StateL.MENU;

    //Methodes:

    //Deze methode laadt alle nodige files;
    protected void loadFiles() {
        //Hier laden we alle files zoals afbeeldingen;
    }

    public class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");
            closeBtn.setId("closebutton");
            closeBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });
            this.getChildren().add(closeBtn);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        //Hernoemen van primaryStage naar window, zodat het duidelijker is;

        window = primaryStage;
        window.initStyle(StageStyle.UNDECORATED);

        thewindow = new Stage(StageStyle.TRANSPARENT);

        Shadoweffect headshadow = new Shadoweffect(0.5);

        Label label1 = new Label("DATA APPLICATION");
        label1.setTranslateY(-75);
        label1.setTranslateX(10);
        label1.setEffect(headshadow.getShadow());


        FadeTransition ft = new FadeEffect(label1).getFadeTransition();

        ScaleTransition st = new ScaleEffect(label1).getScaleEffect();
        st.play();

        //De titel van de window, staat linksbovenin;
        thewindow.setTitle("Data Application");

        AbstractButtonClass startButton = new GeneralButton(0, 0, "Start", e -> thewindow.setScene(Main.chooseScene), false);
        AbstractButtonClass exitButton = new GeneralButton(0, 40, "Exit", e -> {System.out.println("Applicatie wordt afgesloten...");thewindow.close();}, true);
        AbstractButtonClass backButton = new GeneralButton(0, 0, "Back", e -> thewindow.setScene(chooseScene), true);
        AbstractButtonClass backButton2 = new GeneralButton(0, 0, "Back", e -> thewindow.setScene(chooseScene), true);


        /********************
         *  Begin mainMenu  *
         *******************/

        BorderPane mainMenu = new BorderPane();
        mainMenu.getStyleClass().add("bg");

        Draggable.setDraggable(mainMenu);

        StackPane mainstack = new StackPane();

        mainstack.getChildren().addAll(exitButton.getButton(), startButton.getButton(), label1);
        mainMenu.setCenter(mainstack);

        // going to make a class for this

        ToolBar toolBar = new ToolBar();
        toolBar.getStyleClass().add("tool");
        Draggable.setDraggable(toolBar);

        int height = 30;
        toolBar.setPrefHeight(height);
        toolBar.setMinHeight(height);
        toolBar.setMaxHeight(height);
        HBox aligner = new HBox();
        aligner.setHgrow(aligner, Priority.ALWAYS);
        toolBar.getItems().add(aligner);
        toolBar.getItems().add(new WindowButtons());

        ToolBar toolBar2 = new ToolBar();
        toolBar2.getStyleClass().add("tool");
        Draggable.setDraggable(toolBar2);

        toolBar2.setPrefHeight(height);
        toolBar2.setMinHeight(height);
        toolBar2.setMaxHeight(height);
        HBox aligner2 = new HBox();
        aligner2.setHgrow(aligner2, Priority.ALWAYS);
        toolBar2.getItems().add(aligner2);
        toolBar2.getItems().add(new WindowButtons());

        mainMenu.setTop(toolBar);

        mainScene = new Scene(mainMenu, 720, 540);

        /********************
         *  Einde mainMenu  *
         *******************/

        ConnectDatabase mainDataBase = new ConnectDatabase();
        mainDataBase.connect();

        PieGraph g = new PieGraph(mainDataBase.getGarages(), "Garages per deelgemeenten");
        BarGraph b = new BarGraph(mainDataBase.getTheftYear(2009), "2009");
        BarGraph c = new BarGraph(mainDataBase.getTheftYear(2011), "2011");

        AbstractButtonClass backButton1 = new GeneralButton(0, 40, "Back", e -> thewindow.setScene(mainScene), true);
        AbstractButtonClass chooseCarTheft = new GeneralButton(0, 0, "CarTheft", e -> thewindow.setScene(cartheftScene), false);
        AbstractButtonClass chooseGarage = new GeneralButton(0, -40, "Garage", e -> thewindow.setScene(garageScene), false);

        AbstractButtonClass cartheftButton1 = new GeneralButton(0, 0, "2009", e -> Main.cartheftScreen.setCenter(b.getGraph()), false);
        AbstractButtonClass cartheftButton2 = new GeneralButton(0, 0, "2011", e -> Main.cartheftScreen.setCenter(c.getGraph()), false);

        BorderPane cs = new BorderPane();
        StackPane chooseScreen = new StackPane();

        Draggable.setDraggable(chooseScreen);
        chooseScreen.getChildren().addAll(backButton1.getButton(), chooseCarTheft.getButton(), chooseGarage.getButton());
        cs.setCenter(chooseScreen);
        cs.setTop(toolBar2);
        chooseScene = new Scene(cs, 720, 540);

        ToolBar menubarGarage = new ToolBar();
        menubarGarage.getItems().addAll(backButton.getButton());

        /*******************************
         *  Interactieve Garage Graph  *
         *******************************/
        BorderPane garageScreen = new BorderPane();
        garageScene = new Scene(new Group(garageScreen));
        garageScreen.setMinSize(720, 540);
        final ObservableList<Node> children = ((Group) garageScene.getRoot()).getChildren();

        PieChart garagesChart = g.getGraph();
        children.add(garagesChart);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        children.add(caption);

        for (final PieChart.Data data : garagesChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            int integerGarage = (int) data.getPieValue();
                            caption.setText(String.valueOf(integerGarage));
                            caption.setVisible(true);
                        }
                    });
        }

        garageScreen.setCenter(garagesChart);
        garageScreen.setTop(menubarGarage);

        ToolBar menubarCartheft = new ToolBar();
        menubarCartheft.getItems().addAll(backButton2.getButton(), cartheftButton1.getButton(), cartheftButton2.getButton());


        /*********************
         *   Diefstal Graph  *
         ********************/
        cartheftScreen = new BorderPane();
        cartheftScreen.setTop(menubarCartheft);

        cartheftScene = new Scene(cartheftScreen, 720, 540);

        cs.getStylesheets().add("Styling/mainStyle.css");
        cartheftScreen.getStyleClass().add("bg");
        garageScreen.getStyleClass().add("bg");
        cs.getStyleClass().add("bg");


        List<Scene> l = new ArrayList<Scene>();

        l.add(mainScene);
        l.add(chooseScene);
        l.add(cartheftScene);
        l.add(garageScene);

        for (Scene i: l){
            i.getStylesheets().add("Styling/mainStyle.css");
            i.setFill(Color.TRANSPARENT);
        }


        ft.play();
        thewindow.setScene(mainScene);

        //      window.setFullScreen(true);

        //Haalt de "press escape to exit" message weg
//        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        thewindow.show();

        Intro intro = new Intro();
        intro.playit();
    }


    //Hier runnen we het programma;
    public static void main(String[] args) {
        System.out.println("Data application version " + versionNumber + " successfully launched!");
        launch(args);
    }


}
