package Main;

import Events.*;
import Buttons.AbstractButtonClass;
import Buttons.GeneralButton;
import Effects.Shadoweffect;
import Graphs.BarGraph;
import Graphs.PieGraph;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Created by Aaron on 2-4-2017.
 * .gitignore has been instructed to ignore certain files.
 * This file will act as the Main file, we will call all code from this file.
 */

public class Main extends Application {
    public static double versionNumber = 1.0;
    public static Button exitButton, startButton, backButton;
    public static Stage window;
    public static Scene mainScene, carTheftScene, chooseScene;
    public static boolean menuState;
    public static BorderPane mainScreen;

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
        label1.setTranslateY(-75);
        label1.setTranslateX(10);
        label1.setEffect(headshadow.getShadow());
        //De titel van de window, staat linksbovenin;
        window.setTitle("Data Application");

        AbstractButtonClass startButton = new GeneralButton(0, 0, "Start", e -> Main.window.setScene(Main.chooseScene), false);
        AbstractButtonClass exitButton = new GeneralButton(0, 40, "Exit", e -> {System.out.println("Applicatie wordt afgesloten...");Main.window.close();}, true);
        AbstractButtonClass backButton = new GeneralButton(0, 0, "Back", e -> window.setScene(chooseScene), true);

        //mainMenu is nu StackPane, later vervangen zodat we zelf coordinaten kunnen zetten;
        StackPane mainMenu = new StackPane();
        mainMenu.getChildren().addAll(exitButton.getButton(), startButton.getButton(), label1);
        mainScene = new Scene(mainMenu, 720, 576);


        AbstractButtonClass backButton1 = new GeneralButton(0, 40, "Back", e -> window.setScene(mainScene), true);
        AbstractButtonClass chooseCarTheft = new GeneralButton(0, 0, "CarTheft", e -> window.setScene(carTheftScene), false);
        AbstractButtonClass chooseGarage = new GeneralButton(0, -40, "Garage", null, false);

        StackPane chooseScreen = new StackPane();
        chooseScreen.setId("choosescreen");
        chooseScreen.getChildren().addAll(backButton1.getButton(), chooseCarTheft.getButton(), chooseGarage.getButton());
        chooseScreen.getStylesheets().add("Styling/mainStyle.css");
        chooseScene = new Scene(chooseScreen, 720, 576);
        chooseScene.getStylesheets().add("Styling/mainStyle.css");

//        Toolbar top with year choicebox, buttons, checkboxes
        ChoiceBox <String> ChoiceYear = new ChoiceBox<>();
        AbstractButtonClass goButton = new GeneralButton(0,0,"Go", e -> MouseEvents.getChoice(ChoiceYear), false);
        ChoiceYear.getItems().addAll("2012", "2013", "2014");
        ChoiceYear.setValue("2012");
        ChoiceYear.setEffect(new Shadoweffect(0.5).getShadow());

        ToolBar menubar = new ToolBar();
        menubar.getItems().addAll(ChoiceYear, goButton.getButton(), backButton.getButton());

        mainScreen = new BorderPane();
        mainScreen.setTop(menubar);

        carTheftScene = new Scene(mainScreen, 720, 576);
        carTheftScene.setFill(Color.CHOCOLATE);
        mainScene.getStylesheets().add("Styling/mainStyle.css");
        carTheftScene.getStylesheets().add("Styling/mainStyle.css");
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
