package Main;

import Database.ConnectDatabase;
import Buttons.GeneralButton;
import Effects.FadeEffect;
import Effects.ScaleEffect;
import Effects.Shadoweffect;
import Events.MouseEvents;
import Graphs.BarGraph;
import Graphs.PieGraph;
import Modifications.Draggable;

import Tools.*;
import javafx.application.Application;
import javafx.collections.ObservableList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import javafx.scene.image.Image;
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
 * TODO: get maximize screen button working, fade-out when minimizing
 */

public class Main extends Application {
    private static double versionNumber = 1.0;
    public static Stage window;
    private static Scene mainScene, garageScene, chooseScene, cartheftScene, aboutScene, creditsScene;
    public static Stage thewindow;

    //Methodes:

    @Override
    public void start(Stage primaryStage) {
        //Hernoemen van primaryStage naar window, zodat het duidelijker is;

        window = primaryStage;
        window.initStyle(StageStyle.UNDECORATED);
        Image icon = new Image(Main.class.getResourceAsStream("/icon.png"));
        thewindow = new Stage(StageStyle.TRANSPARENT);
        thewindow.setTitle("Data Application");
        thewindow.getIcons().add(icon);

        Label label1 = new Label("DATA APPLICATION");
        label1.setTranslateY(-175);
        label1.setTranslateX(0);
        label1.setEffect(new Shadoweffect(0.5).getShadow());

        FadeEffect.getFadeTransition(label1).play();
        ScaleEffect.getScaleEffect(label1).play();

        /********************
         *  Begin MainMenu  *
         *******************/

        GeneralScreen mm = new GeneralScreen();
        BorderPane mainMenu = mm.getbPane();
        StackPane mainstack = mm.getsPane();

        mainMenu.getStyleClass().add("bg");

        mainstack.getChildren().addAll(new GeneralButton(0, -90, "Start", e -> thewindow.setScene(chooseScene), false).getButton(), new GeneralButton(0, 50, "Credits", e -> thewindow.setScene(creditsScene), false).getButton(), new GeneralButton(0, -20, "About", e -> thewindow.setScene(aboutScene), false).getButton(), new GeneralButton(0, 120, "Exit", e -> {System.out.println("Applicatie wordt afgesloten...");thewindow.close();}, true).getButton(), label1);
        mainMenu.setCenter(mainstack);

        ToolBar MainMenubar = mm.getTBar();
        MainMenubar.getItems().addAll(new Icon(), new WindowToolBar().getAligner(), new MinimizeButton(false), new CloseButton(false));

        mainScene = mm.setUpScene(mainMenu, mainstack, MainMenubar);

        /********************
         *  About Screen    *a-neue");
        aboutlabel.setTranslateX(10);
         *******************/
        Label abouttitle = new Label();
        abouttitle.setEffect(new Shadoweffect(0.1).getShadow());
        abouttitle.setText("About Program");
        abouttitle.setTranslateY(-180);
        abouttitle.setStyle("-fx-font-size: 30; -fx-font-family: Helvetica-neue");
        Label aboutlabel = new Label();
        aboutlabel.setTranslateY(18);
        aboutlabel.setWrapText(true);
        aboutlabel.setMaxWidth(400);
        aboutlabel.setStyle("-fx-font-size: 15; -fx-font-family: Helvetica-neue");
        aboutlabel.setText("This application shows info in the form of graphs about the amount of garages and car theft in Rotterdam. \n" +
                "The garage page shows the amount of garages per city district in the form of a pie graph.\n" +
                "The car theft page shows the percentage of citizens(15 years and older) per district with a car who have dealt with car theft. This info can be seen from the year 2009 and 2011 in the form of a bar graph.  \n");

        GeneralScreen about = new GeneralScreen();
        BorderPane as = about.getbPane();
        StackPane aboutScreen = about.getsPane();
        ToolBar aboutbar = about.getTBar();

        aboutbar.getItems().addAll(new Icon(), new WindowToolBar().getAligner(), new MinimizeButton(false), new CloseButton(false));
        aboutScreen.getChildren().addAll(new GeneralButton(0, 220, "Back", e -> thewindow.setScene(mainScene), true).getButton(), abouttitle, aboutlabel);

        aboutScene = about.setUpScene(as, aboutScreen, aboutbar);

        /********************
         *  Credits Screen  *
         *******************/
        Label creditstitle = new Label();
        creditstitle.setEffect(new Shadoweffect(0.5).getShadow());
        creditstitle.setText("Credits");
        creditstitle.setTranslateY(-180);
        Label creditslabel = new Label();
        creditslabel.setTranslateY(18);
        creditslabel.setWrapText(true);
        creditslabel.setMaxWidth(400);
        creditslabel.setStyle("-fx-font-size: 15; -fx-font-family: Helvetica-neue");
        creditslabel.setTranslateX(10);
        creditslabel.setText("Aaron Beetstra: Development Team\n - Graph code, connection with the database, uml diagrams.\n Ralph Verburg: Development Team\n - Menu code, buttons code, basic scene building.\n Mark Stout: Development team\n - Create Database, SQL, UML diagram.\n Selim Aydi: Development Team\n - CSS, styling, effects code, tools code, bug fixing.\n Ryan Wilson: Scrum Master\n - Scrum tasks, CSV parser, ERD and RM diagram.");

        GeneralScreen credits = new GeneralScreen();
        BorderPane creditsScreen = credits.getbPane();
        StackPane creditspane = credits.getsPane();
        ToolBar creditsbar = credits.getTBar();

        creditsbar.getItems().addAll(new Icon(), new WindowToolBar().getAligner(), new MinimizeButton(false), new CloseButton(false));
        creditspane.getChildren().addAll(new GeneralButton(0, 220, "Back", e -> thewindow.setScene(mainScene), true).getButton(), creditstitle, creditslabel);

        creditsScene = credits.setUpScene(creditsScreen, creditspane, creditsbar);

        /************************
         *  Database Verbinding *
         ***********************/

        ConnectDatabase mainDataBase = new ConnectDatabase();
        mainDataBase.connect();

        PieGraph g = new PieGraph(mainDataBase.getGarages(), "Garages per city district");
        BarGraph b = new BarGraph(mainDataBase.getTheftYear(2009));
        BarGraph c = new BarGraph(mainDataBase.getTheftYear(2011));

        /*********************
         *   Diefstal Graph  *
         ********************/
        Label thefttitle = new Label();
        thefttitle.setEffect(new Shadoweffect(0.5).getShadow());
        thefttitle.setText("Choose an option above");
        thefttitle.setTranslateY(-180);
        Label theftlabel = new Label();
        theftlabel.setTranslateY(18);
        theftlabel.setWrapText(true);
        theftlabel.setMaxWidth(400);
        theftlabel.setStyle("-fx-font-size: 15; -fx-font-family: Helvetica-neue");
        theftlabel.setTranslateX(10);
        theftlabel.setText("You can choose between car theft data from either 2009 or 2011");

        GeneralScreen ct = new GeneralScreen();
        BorderPane cartheftScreen = ct.getbPane();
        StackPane cartheftpane = ct.getsPane();
        cartheftpane.getChildren().addAll(thefttitle, theftlabel);
        cartheftScreen.setMinSize(720, 540);

        ToolBar menubarCartheft = ct.getTBar();

        cartheftScreen.setTop(menubarCartheft);
        cartheftScreen.setCenter(cartheftpane);
        cartheftScene = new Scene(new Group(cartheftScreen));

        final ObservableList<Node> childerenCarTheft = ((Group) cartheftScene.getRoot()).getChildren();

        //Buttons op de auto diefstal page
        GeneralButton cartheftButton1 = new GeneralButton(10, 0, "2009", e -> {
            BarChart diefstal2009 = b.getGraph();
            childerenCarTheft.add(diefstal2009);
            MouseEvents.getValueBarChart(diefstal2009, childerenCarTheft, true);
            cartheftScreen.setCenter(diefstal2009);
        }, false);


        GeneralButton cartheftButton2 = new GeneralButton(20, 0, "2011", e -> {
            BarChart diefstal2011 = c.getGraph();
            childerenCarTheft.add(diefstal2011);
            MouseEvents.getValueBarChart(diefstal2011, childerenCarTheft, true);
            cartheftScreen.setCenter(diefstal2011);
        }, false);

        menubarCartheft.getItems().addAll(new Icon(), new GeneralButton(0, 0, "Back", e -> {thewindow.setScene(chooseScene); cartheftScreen.setCenter(cartheftpane);}, true).getButton(), cartheftButton1.getButton(), cartheftButton2.getButton(), new WindowToolBar().getAligner(), new MinimizeButton(true), new CloseButton(true));

        /***************************
         *      Choose Screen      *
         **************************/

        Label chooselabel = new Label("Menu");
        chooselabel.setStyle("-fx-font-family: Helvetica-neue; -fx-font-size: 40;");
        chooselabel.setTranslateY(-175);
        chooselabel.setTranslateX(0);
        chooselabel.setEffect(new Shadoweffect(0.5).getShadow());

        GeneralScreen choose = new GeneralScreen();
        BorderPane cs = choose.getbPane();
        StackPane chooseScreen = choose.getsPane();
        ToolBar menubarcs = choose.getTBar();

        menubarcs.getItems().addAll(new Icon(), new WindowToolBar().getAligner(), new MinimizeButton(false), new CloseButton(false));
        chooseScreen.getChildren().addAll(new GeneralButton(0, 110, "Back", e -> thewindow.setScene(mainScene), true).getButton(), new GeneralButton(0, 20, "CarTheftInfo", e -> thewindow.setScene(cartheftScene), false).getButton(), new GeneralButton(0, -70, "GarageInfo", e -> thewindow.setScene(garageScene), false).getButton(), chooselabel);

        chooseScene = choose.setUpScene(cs, chooseScreen, menubarcs);

        /*******************************
         *  Interactieve Garage Graph  *
         *******************************/

        BorderPane garageScreen = new BorderPane();
        Draggable.setDraggable(garageScreen);

        GeneralButton backbutton = new GeneralButton(0, 0, "Back", e -> thewindow.setScene(chooseScene), true);
        Button bb = backbutton.getButton();
        bb.setTranslateX(230);
        bb.setTranslateY(-1);

        ToolBar menubarGarage = new ToolBar();
        Draggable.setDraggable(menubarGarage);
        menubarGarage.getStyleClass().add("tool");
        menubarGarage.getItems().addAll(new Icon(), backbutton.getButton(), new WindowToolBar().getAligner(), new MinimizeButton(true), new CloseButton(true));

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
        cs.getStyleClass().add("bg");
        as.getStylesheets().add("Styling/mainStyle.css");
        as.getStyleClass().add("bg");

        cartheftScreen.getStyleClass().add("bg2");
        garageScreen.getStyleClass().add("bg2");
        creditsScreen.getStyleClass().add("bg");
        creditsScreen.getStyleClass().add("bg");


        List<Scene> l = new ArrayList<>();
        l.add(mainScene);
        l.add(chooseScene);
        l.add(cartheftScene);
        l.add(garageScene);
        l.add(aboutScene);
        l.add(creditsScene);

        for (Scene i: l){
            i.getStylesheets().add("Styling/mainStyle.css");
            i.setFill(Color.TRANSPARENT);
        }

        thewindow.setScene(mainScene);

        thewindow.show();

    }

    //Hier runnen we het programma;
    public static void main(String[] args) {
        System.out.println("Data application version " + versionNumber + " successfully launched!");
        launch(args);
    }

}
