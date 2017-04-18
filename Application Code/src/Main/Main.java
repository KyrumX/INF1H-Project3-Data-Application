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
import Tools.GeneralScreen;
import Tools.MinimizeButton;
import Tools.WindowToolBar;
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
    private static double versionNumber = 1.0;
    public static Stage window;
    private static Scene mainScene, garageScene, chooseScene, cartheftScene, aboutScene, creditsScene;
    public static BorderPane mainMenu, garageScreen;
    public static Stage thewindow;

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
        thewindow.setTitle("Data Application");

        Label label1 = new Label("DATA APPLICATION");
        label1.setTranslateY(-75);
        label1.setTranslateX(10);
        label1.setEffect(new Shadoweffect(0.5).getShadow());

        AbstractButtonClass aboutButton = new GeneralButton(0, 40, "About", e -> thewindow.setScene(aboutScene), false);
        AbstractButtonClass creditsButton = new GeneralButton(0, 80, "Credits", e -> thewindow.setScene(creditsScene), false);

        FadeEffect.getFadeTransition(label1).play();
        ScaleEffect.getScaleEffect(label1).play();

        /********************
         *  Begin mainMenu  *
         *******************/

        GeneralScreen mm = new GeneralScreen();
        BorderPane mainMenu = mm.getbPane();
        StackPane mainstack = mm.getsPane();

        mainMenu.getStyleClass().add("bg");

        mainstack.getChildren().addAll(new GeneralButton(0, 40, "Exit", e -> {System.out.println("Applicatie wordt afgesloten...");thewindow.close();}, true).getButton(), new GeneralButton(0, 0, "Start", e -> thewindow.setScene(chooseScene), false).getButton(), creditsButton.getButton(), aboutButton.getButton(), label1);
        mainMenu.setCenter(mainstack);

        ToolBar MainMenubar = mm.getTBar();
        MainMenubar.getItems().addAll(new MinimizeButton(false), new WindowToolBar().getAligner(), new CloseButton(false));

        mainScene = mm.setUpScene(mainMenu, mainstack, MainMenubar);

        BorderPane aboutScreen = new BorderPane();
        aboutScene = new Scene(aboutScreen, 720, 540);

        BorderPane creditsScreen = new BorderPane();
        aboutScene = new Scene(creditsScreen, 720, 540);


        /********************
         *  Einde mainMenu  *
         *******************/

        ConnectDatabase mainDataBase = new ConnectDatabase();
        mainDataBase.connect();

        PieGraph g = new PieGraph(mainDataBase.getGarages(), "Garages per deelgemeenten");
        BarGraph b = new BarGraph(mainDataBase.getTheftYear(2009), "2009");
        BarGraph c = new BarGraph(mainDataBase.getTheftYear(2011), "2011");

        AbstractButtonClass chooseCarTheft = new GeneralButton(0, 0, "CarTheft", e -> thewindow.setScene(cartheftScene), false);
        AbstractButtonClass chooseGarage = new GeneralButton(0, -40, "Garage", e -> thewindow.setScene(garageScene), false);

        /*********************
         *   Diefstal Graph  *
         ********************/

        GeneralScreen ct = new GeneralScreen();
        BorderPane cartheftScreen = ct.getbPane();
        cartheftScene = new Scene(new Group(cartheftScreen));
        cartheftScreen.setMinSize(720, 540);

        ToolBar menubarCartheft = ct.getTBar();

        cartheftScreen.setTop(menubarCartheft);

        final ObservableList<Node> childerenCarTheft = ((Group) cartheftScene.getRoot()).getChildren();

        //Buttons op de auto diefstal page
        AbstractButtonClass cartheftButton1 = new GeneralButton(0, 0, "2009", e -> {
            BarChart diefstal2009 = b.getGraph();
            childerenCarTheft.add(diefstal2009);
            MouseEvents.getValueBarChart(diefstal2009, childerenCarTheft, true);
            cartheftScreen.setCenter(diefstal2009);
        }, false);
        AbstractButtonClass cartheftButton2 = new GeneralButton(0, 0, "2011", e -> {
            BarChart diefstal2011 = c.getGraph();
            childerenCarTheft.add(diefstal2011);
            MouseEvents.getValueBarChart(diefstal2011, childerenCarTheft, true);
            cartheftScreen.setCenter(diefstal2011);
        }, false);

        menubarCartheft.getItems().addAll(new GeneralButton(0, 0, "Back", e -> thewindow.setScene(chooseScene), true).getButton(), cartheftButton1.getButton(), cartheftButton2.getButton(), new WindowToolBar().getAligner(), new MinimizeButton(true), new CloseButton(true));

        /***************************
         *      Choose Screen      *
         **************************/

        GeneralScreen choose = new GeneralScreen();
        BorderPane cs = choose.getbPane();
        StackPane chooseScreen = choose.getsPane();
        ToolBar menubarcs = choose.getTBar();

        menubarcs.getItems().addAll(new MinimizeButton(false), new WindowToolBar().getAligner(), new CloseButton(false));
        chooseScreen.getChildren().addAll(new GeneralButton(0, 40, "Back", e -> thewindow.setScene(mainScene), true).getButton(), chooseCarTheft.getButton(), chooseGarage.getButton());

        chooseScene = choose.setUpScene(cs, chooseScreen, menubarcs);

        /*******************************
         *  Interactieve Garage Graph  *
         *******************************/

        BorderPane garageScreen = new BorderPane();
        Draggable.setDraggable(garageScreen);

        ToolBar menubarGarage = new ToolBar();
        Draggable.setDraggable(menubarGarage);
        menubarGarage.getStyleClass().add("tool");
        menubarGarage.getItems().addAll(new GeneralButton(0, 0, "Back", e -> thewindow.setScene(chooseScene), true).getButton(), new WindowToolBar().getAligner(), new MinimizeButton(true), new CloseButton(true));

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

        thewindow.setScene(mainScene);

        // window.setFullScreen(true);

        // Haalt de "press escape to exit" message weg
        // window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        thewindow.show();

    }

    //Hier runnen we het programma;
    public static void main(String[] args) {
        System.out.println("Data application version " + versionNumber + " successfully launched!");
        launch(args);
    }

}
