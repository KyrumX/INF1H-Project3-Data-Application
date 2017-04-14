import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author zoranpavlovic.blogspot.com
 */
public class LoadImage extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Load Image");

        StackPane sp = new StackPane();
        Image img = new Image("http://cdn.thedesignwork.com/wp-content/uploads/2011/10/Random-Pictures-of-Conceptual-and-Creative-Ideas-02.jpg");
        ImageView imgView = new ImageView(img);
        imgView.setImage(img);
        imgView.setTranslateX(0);
        imgView.setTranslateY(0);
        FadeTransition ft = new FadeTransition();
        ft.setNode(imgView);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(6);
        ft.setAutoReverse(true);
        sp.getChildren().add(imgView);
        ft.play();
        //Adding HBox to the scene
        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}