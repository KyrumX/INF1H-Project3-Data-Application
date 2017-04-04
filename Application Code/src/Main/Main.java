package Main;

import Events.MouseEvents;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Aaron on 2-4-2017.
 * .gitignore has been instructed to ignore certain files.
 * This file will act as the Main file, we will call all code from this file.
 */

public class Main extends Application {
    public static double versionNumber = 1.0;
    public static Button exitButton;
    public Stage window;

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

        //De titel van de window, staat linksbovenin;
        window.setTitle("Data Application");

        //Het aanmaken van een nieuwe button;
        exitButton = new Button();
        //Text van de button;
        exitButton.setText("Exit");
        //Plek waar de button zijn actie zoekt;
        exitButton.setOnAction(new MouseEvents());

        //Layout is nu StackPane, later vervangen zodat we zelf coordinaten kunnen zetten;
        StackPane mainMenu = new StackPane();
        mainMenu.getChildren().add(exitButton);
        Scene scene = new Scene(mainMenu, 1920, 1080);
        window.setScene(scene);
        window.show();
    }






    //Hier runnen we het programma;
    public static void main(String[] args) {
        launch(args);
        System.out.println("Data application version " + versionNumber + " successfully launched!");
    }

}
