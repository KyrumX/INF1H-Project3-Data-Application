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
 * I am teaching you how to merge 101.
 */

public class Main extends Application {
    public static double versionNumber = 1.0;
    public static Button exitButton;
    public Stage window;

    public static void main(String[] args) {
        launch(args);
        System.out.println("Data application version " + versionNumber + " successfully launched!");
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
        StackPane layout = new StackPane();
        layout.getChildren().add(exitButton);
        Scene scene = new Scene(layout, 1920, 1080);
        window.setScene(scene);
        window.show();
    }

}
