package Events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Main.Main;

/**
 * Created by aaron on 4-4-2017.
 */

public class MouseEvents implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource().equals(Main.exitButton)) {
            System.out.println("Applicatie wordt afgesloten...");
            System.exit(1);
        }
    }
}
