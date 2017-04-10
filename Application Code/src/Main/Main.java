package Main;

import Events.*;
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
    public static Scene mainScene, carTheftScene;
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

        Label label1 = new Label("DATA APPLICATION");
        label1.setTranslateY(-85);
        label1.setEffect(headshadow.getShadow());
        //De titel van de window, staat linksbovenin;
        window.setTitle("Data Application");

        AbstractButtonClass startButton = new GeneralButton(0, 0, "Start", e -> Main.window.setScene(Main.carTheftScene), false);
        AbstractButtonClass exitButton = new GeneralButton(0, 40, "Exit", e -> {System.out.println("Applicatie wordt afgesloten...");Main.window.close();}, true);
        AbstractButtonClass backButton = new GeneralButton(0, 0, "Back", e -> window.setScene(mainScene), true);

        //mainMenu is nu StackPane, later vervangen zodat we zelf coordinaten kunnen zetten;
        StackPane mainMenu = new StackPane();
        mainMenu.getChildren().addAll(exitButton.getButton(), startButton.getButton(), label1);
        mainScene = new Scene(mainMenu, 720, 576);

//        Toolbar top with year choicebox, buttons, checkboxes
        ChoiceBox <String> ChoiceYear = new ChoiceBox<>();
        AbstractButtonClass goButton = new GeneralButton(0,0,"Go", e -> MouseEvents.getChoice(ChoiceYear), false);
        ChoiceYear.getItems().addAll("2012", "2013", "2014");
        ChoiceYear.setValue("2012");
        CheckBox cb1 = new CheckBox("First");
        cb1.setSelected(true);
        ChoiceYear.setEffect(new Shadoweffect(0.5).getShadow());

        ToolBar menubar = new ToolBar();
        menubar.getItems().addAll(ChoiceYear, goButton.getButton(), backButton.getButton(), cb1);

        BorderPane mainScreen = new BorderPane();
        mainScreen.setTop(menubar);

        //PieGraph trials
        HashMap<String, Double> myFriends = new HashMap<String, Double>();

        myFriends.put("2007", 50.0);
        myFriends.put("2008", 25.0);
        myFriends.put("2009", 25.0);
        BarGraph g = new BarGraph(myFriends, "Centrum");
        mainScreen.setCenter(g.getGraph());
        //Einde PieGraph trials

        carTheftScene = new Scene(mainScreen, 720, 576);

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
