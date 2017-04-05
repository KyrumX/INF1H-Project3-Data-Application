package Main;

import Effects.Shadoweffect;
import Events.MouseEvents;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
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
    public static Scene scene1, scene2;
    public static boolean menuState;

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
        Shadoweffect buttonshadow = new Shadoweffect(0.5);

        Label label1 = new Label("Kappameisters");
        label1.setTranslateY(-85);
        label1.setEffect(headshadow.getShadow());
        //De titel van de window, staat linksbovenin;
        window.setTitle("Data Application");

        //Het aanmaken van een nieuwe button;
        exitButton = new Button();
        //Text van de button;
        exitButton.setText("Exit");
        //Plek waar de button zijn actie zoekt;
        exitButton.setOnAction(new MouseEvents());
        exitButton.setTranslateX(0);
        exitButton.setTranslateY(40);
        exitButton.setEffect(buttonshadow.getShadow());
        exitButton.getStyleClass().add("exitButton");

        startButton = new Button();
        startButton.setText("Launch");
        startButton.setOnAction(new MouseEvents());
        startButton.setEffect(buttonshadow.getShadow());

        backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> window.setScene(scene1));



        //Layout is nu StackPane, later vervangen zodat we zelf coordinaten kunnen zetten;
        StackPane mainMenu = new StackPane();
        StackPane mainScreen = new StackPane();
        mainMenu.getChildren().addAll(exitButton, startButton, label1);
        mainScreen.getChildren().add(backButton);

        scene1 = new Scene(mainMenu, 300, 250);
        scene2 = new Scene(mainScreen, 300, 250);
        scene1.getStylesheets().add("Main/style.css");
        scene2.getStylesheets().add("Main/style.css");
        window.setScene(scene1);
        window.show();


    }


    //Hier runnen we het programma;
    public static void main(String[] args) {
        launch(args);
        System.out.println("Data application version " + versionNumber + " successfully launched!");
    }

}
