package Database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by aaron on 5-4-2017.
 */
public class ConnectDatabase {
    private final String url = "jdbc:postgresql://localhost/project2";
    private final String user = "postgres";
    private final String password = "kaas123";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        ConnectDatabase trial = new ConnectDatabase();
        trial.connect();
    }
}
