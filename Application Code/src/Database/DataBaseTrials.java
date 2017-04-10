package Database;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by aaron on 10-4-2017.
 */
public class DataBaseTrials {
    public static void main(String[] args) {
        ConnectDatabase trial = new ConnectDatabase();
        trial.connect();
        trial.getGarages();
    }
}
