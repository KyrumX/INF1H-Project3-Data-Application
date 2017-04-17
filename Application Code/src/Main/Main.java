package Main;

import Database.ConnectDatabase;
import Buttons.AbstractButtonClass;
import Buttons.GeneralButton;
import Effects.FadeEffect;
import Effects.ScaleEffect;
import Effects.Shadoweffect;
import Events.MouseEvents;
import Graphs.BarGraph;
import Graphs.PieGraph;
import Modifications.Draggable;

import Tools.CloseButton;
import Tools.MinimizeButton;
import Tools.WindowToolBar;
import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.ObservableList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

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
 *
 * TODO: get maximize screen button working, icon for program, fade-out when minimizing, initial info scene cartheftscene
 */

public class Main extends Application {
    public static double versionNumber = 1.0;
    public static AbstractButtonClass exitButton, startButton;
    public static Stage window;
    public static Scene mainScene, garageScene, chooseScene, cartheftScene;
    public static boolean menuState;
    public static BorderPane cartheftScreen, mainMenu, garageScreen, cs;
    public static Stage thewindow;
    final double initialSceneWidth = 720;
    final double initialSceneHeight = 640;

    //Bepalen van de State van het programma;
    private enum StateL{
        MAIN,
        J2012,
        J2009
    }

    private StateL currentState = StateL.MAIN;

    //Methodes:

    //Deze methode laadt alle nodige files;
    protected void loadFiles() {
        //Hier laden we alle files zoals afbeeldingen;
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

        ToolBar MainMenubar = new ToolBar();
        Draggable.setDraggable(MainMenubar);
        MainMenubar.getStyleClass().add("tool");
        MainMenubar.getItems().addAll(new MinimizeButton(false), new WindowToolBar().getAligner(), new CloseButton(false));


        mainMenu.setTop(MainMenubar);

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

        /*********************
         *   Diefstal Graph  *
         ********************/

        cartheftScreen = new BorderPane();
        Draggable.setDraggable(cartheftScreen);
        cartheftScene = new Scene(new Group(cartheftScreen));
        cartheftScreen.setMinSize(720, 540);

        ToolBar menubarCartheft = new ToolBar();
        Draggable.setDraggable(menubarCartheft);
        menubarCartheft.getStyleClass().add("tool");

        cartheftScreen.setTop(menubarCartheft);

        final ObservableList<Node> childerenCarTheft = ((Group) cartheftScene.getRoot()).getChildren();

        //Buttons op de auto diefstal page
        AbstractButtonClass cartheftButton1 = new GeneralButton(0, 0, "2009", e -> {
            BarChart diefstal2009 = b.getGraph();
            childerenCarTheft.add(diefstal2009);
            MouseEvents.getValueBarChart(diefstal2009, childerenCarTheft, true);
            Main.cartheftScreen.setCenter(diefstal2009);
        }, false);
        AbstractButtonClass cartheftButton2 = new GeneralButton(0, 0, "2011", e -> {
            BarChart diefstal2011 = c.getGraph();
            childerenCarTheft.add(diefstal2011);
            MouseEvents.getValueBarChart(diefstal2011, childerenCarTheft, true);
            Main.cartheftScreen.setCenter(diefstal2011);
        }, false);

        menubarCartheft.getItems().addAll(backButton2.getButton(), cartheftButton1.getButton(), cartheftButton2.getButton(), new WindowToolBar().getAligner(), new MinimizeButton(true), new CloseButton(true));

        /***************************
         *      Choose Screen      *
         **************************/

        BorderPane cs = new BorderPane();
        StackPane chooseScreen = new StackPane();
        Draggable.setDraggable(chooseScreen);

        ToolBar menubarcs = new ToolBar();
        Draggable.setDraggable(menubarcs);
        menubarcs.getStyleClass().add("tool");
        menubarcs.getItems().addAll(new MinimizeButton(false), new WindowToolBar().getAligner(), new CloseButton(false));

        chooseScreen.getChildren().addAll(backButton1.getButton(), chooseCarTheft.getButton(), chooseGarage.getButton());
        cs.setCenter(chooseScreen);

        cs.setTop(menubarcs);
        chooseScene = new Scene(cs, 720, 540);

        /*******************************
         *  Interactieve Garage Graph  *
         *******************************/

        BorderPane garageScreen = new BorderPane();
        Draggable.setDraggable(garageScreen);

        ToolBar menubarGarage = new ToolBar();
        Draggable.setDraggable(menubarGarage);
        menubarGarage.getStyleClass().add("tool");
        menubarGarage.getItems().addAll(backButton.getButton(), new WindowToolBar().getAligner(), new MinimizeButton(true), new CloseButton(true));

        garageScene = new Scene(new Group(garageScreen));
        garageScreen.setMinSize(720, 540);
        final ObservableList<Node> childrenGarages = ((Group) garageScene.getRoot()).getChildren();

        PieChart garagesChart = g.getGraph();
        childrenGarages.add(garagesChart);

        //Roep de handler op die het aantal garages toont wanneer er op een chart deel geklikt wordt;
        MouseEvents.getValuePieChart(garagesChart, childrenGarages, false);

        garageScreen.setCenter(garagesChart);

        garageScreen.setTop(menubarGarage);

        /***************************
         *       Stylesheets       *
         ***************************/

        cs.getStylesheets().add("Styling/mainStyle.css");
        cartheftScreen.getStyleClass().add("bg");
        garageScreen.getStyleClass().add("bg");
        cs.getStyleClass().add("bg");


        List<Scene> l = new ArrayList<>();
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
