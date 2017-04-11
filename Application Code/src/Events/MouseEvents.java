package Events;

import Database.ConnectDatabase;
import Graphs.BarGraph;
import Graphs.PieGraph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Main.Main;
import javafx.scene.control.ChoiceBox;

import java.util.HashMap;

/**
 * Created by aaron on 4-4-2017.
 */

public class MouseEvents implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
//        if (event.getSource().equals(Main.exitButton)) {
//            System.out.println("Applicatie wordt afgesloten...");
//            Main.window.close();
//        } else if (event.getSource().equals(Main.startButton)) {
//            Main.window.setScene(Main.scene2);
//        }
    }
    public static void getChoice(ChoiceBox<String> ChoiceYear){
        //PieGraph trials
        ChoiceYear.getSelectionModel().select(0);{
//            ConnectDatabase mainDataBase = new ConnectDatabase();
//            mainDataBase.connect();
//            PieGraph g = new PieGraph(mainDataBase.getGarages(), "Garages per deelgemeenten");

//            HashMap<String, Double> myFriends = new HashMap<String, Double>();
//
//            myFriends.put("2007", 50.0);
//            myFriends.put("2008", 25.0);
//            myFriends.put("2009", 25.0);
//            PieGraph g = new PieGraph(myFriends, "Garages per deelgemeenten");
//            Main.mainScreen.setCenter(g.getGraph());
        }
    }
}


