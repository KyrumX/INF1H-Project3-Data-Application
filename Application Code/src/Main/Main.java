package Main;

import Events.*;
import Buttons.AbstractButtonClass;
import Buttons.GeneralButton;
import Effects.Shadoweffect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javax.swing.text.html.HTML.Tag.HEAD;

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

        Label label1 = new Label("Kappameisters");
        label1.setTranslateY(-85);
        label1.setEffect(headshadow.getShadow());
        //De titel van de window, staat linksbovenin;
        window.setTitle("Data Application");

        AbstractButtonClass startButton = new GeneralButton(0, 0, "Start", e -> Main.window.setScene(Main.scene2), false);
        AbstractButtonClass exitButton = new GeneralButton(0, 40, "Exit", e -> {System.out.println("Applicatie wordt afgesloten...");Main.window.close();}, true);
        AbstractButtonClass backButton = new GeneralButton(0, 0, "Back", e -> window.setScene(scene1), true);

        //Layout is nu StackPane, later vervangen zodat we zelf coordinaten kunnen zetten;
        StackPane mainMenu = new StackPane();
        mainMenu.getChildren().addAll(exitButton.getButton(), startButton.getButton(), label1);
        scene1 = new Scene(mainMenu, 720, 576);

        //topmenu with year choicebox
        HBox topMenu = new HBox(279);

        ChoiceBox <String> ChoiceYear = new ChoiceBox<>();
        AbstractButtonClass A = new GeneralButton(0,0,"Go", e -> MouseEvents.getChoice(ChoiceYear), false);
        ChoiceYear.getItems().addAll("2012", "2013", "2014");
        ChoiceYear.setValue("2012");
        ChoiceYear.setEffect(new Shadoweffect(0.5).getShadow());

        topMenu.getChildren().addAll(backButton.getButton(), ChoiceYear, A.getButton());


        BorderPane mainScreen = new BorderPane();
        mainScreen.setTop(topMenu);
        scene2 = new Scene(mainScreen, 720, 576);


        scene1.getStylesheets().add("Main/style.css");
        scene2.getStylesheets().add("Main/style.css");
        window.setScene(scene1);
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
