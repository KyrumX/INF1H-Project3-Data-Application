package Main;

import Database.ConnectDatabase;
import Buttons.AbstractButtonClass;
import Buttons.GeneralButton;
import Effects.Shadoweffect;
import Graphs.BarGraph;
import Graphs.PieGraph;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Aaron on 2-4-2017.
 * .gitignore has been instructed to ignore certain files.
 * This file will act as the Main file, we will call all code from this file.
 */

public class Main extends Application {
    public static double versionNumber = 1.0;
    public static Button exitButton, startButton, backButton;
    public static Stage window;
    public static Scene mainScene, garageScene, chooseScene, cartheftScene;
    public static boolean menuState;
    public static BorderPane mainScreen, cartheftScreen;

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

    @Override
    public void start(Stage primaryStage) {
        //Hernoemen van primaryStage naar window, zodat het duidelijker is;
        window = primaryStage;

        Shadoweffect headshadow = new Shadoweffect(0.5);

        Label label1 = new Label("DATA APPLICATION");
        label1.setTranslateY(-85);
        label1.setEffect(headshadow.getShadow());
        //De titel van de window, staat linksbovenin;
        window.setTitle("Data Application");

        AbstractButtonClass startButton = new GeneralButton(0, 0, "Start", e -> Main.window.setScene(Main.chooseScene), false);
        AbstractButtonClass exitButton = new GeneralButton(0, 40, "Exit", e -> {System.out.println("Applicatie wordt afgesloten...");Main.window.close();}, true);
        AbstractButtonClass backButton = new GeneralButton(0, 0, "Back", e -> window.setScene(chooseScene), true);
        AbstractButtonClass backButton2 = new GeneralButton(0, 0, "Back", e -> window.setScene(chooseScene), true);


        //mainMenu is nu StackPane, later vervangen zodat we zelf coordinaten kunnen zetten;
        StackPane mainMenu = new StackPane();
        mainMenu.getChildren().addAll(exitButton.getButton(), startButton.getButton(), label1);
        mainScene = new Scene(mainMenu, 720, 540);

        ConnectDatabase mainDataBase = new ConnectDatabase();
        mainDataBase.connect();

        PieGraph g = new PieGraph(mainDataBase.getGarages(), "Garages per deelgemeenten");
        BarGraph b = new BarGraph(mainDataBase.getTheftYear(2009), "2009");
        BarGraph c = new BarGraph(mainDataBase.getTheftYear(2011), "2011");

        AbstractButtonClass backButton1 = new GeneralButton(0, 40, "Back", e -> window.setScene(mainScene), true);
        AbstractButtonClass chooseCarTheft = new GeneralButton(0, 0, "CarTheft", e -> window.setScene(cartheftScene), false);
        AbstractButtonClass chooseGarage = new GeneralButton(0, -40, "Garage", e -> window.setScene(garageScene), false);

        AbstractButtonClass cartheftButton1 = new GeneralButton(0, 0, "2009", e -> Main.cartheftScreen.setCenter(b.getGraph()), false);
        AbstractButtonClass cartheftButton2 = new GeneralButton(0, 0, "2011", e -> Main.cartheftScreen.setCenter(c.getGraph()), false);

        StackPane chooseScreen = new StackPane();
        chooseScreen.getChildren().addAll(backButton1.getButton(), chooseCarTheft.getButton(), chooseGarage.getButton());
        chooseScene = new Scene(chooseScreen, 720, 540);

        ToolBar menubarGarage = new ToolBar();
        menubarGarage.getItems().addAll(backButton.getButton());

        mainScreen = new BorderPane();
        mainScreen.setTop(menubarGarage);
        Main.mainScreen.setCenter(g.getGraph());

        garageScene = new Scene(mainScreen, 720, 576);

        ToolBar menubarCartheft = new ToolBar();
        menubarCartheft.getItems().addAll(backButton2.getButton(), cartheftButton1.getButton(), cartheftButton2.getButton());

        cartheftScreen = new BorderPane();
        cartheftScreen.setTop(menubarCartheft);

        cartheftScene = new Scene(cartheftScreen, 720, 540);


        mainScene.getStylesheets().add("Styling/mainStyle.css");
        garageScene.getStylesheets().add("Styling/mainStyle.css");
        cartheftScene.getStylesheets().add("Styling/mainStyle.css");
        chooseScreen.getStylesheets().add("Styling/mainStyle.css");
        window.setScene(mainScene);
        //      window.setFullScreen(true);

        //Haalt de "press escape to exit" message weg
        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        window.show();
    }


    //Hier runnen we het programma;
    public static void main(String[] args) {
        System.out.println("Data application version " + versionNumber + " successfully launched!");
        launch(args);
    }

}
