package Database;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by aaron on 5-4-2017.
 */
public class ConnectDatabase {
    private final String url = "jdbc:postgresql://localhost/Project3";
    private final String user = "postgres";
    private final String password = "kaas123";
    private Connection conn;

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        this.conn = conn;
        return conn;
    }

    public HashMap getGarages() {
        HashMap<String, Double> newHashMap = new HashMap<String, Double>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT deelgemeente, COUNT(garagenaam) FROM garages GROUP BY deelgemeente");
            while ( rs.next() ) {
                String deelGemeenteNaam = rs.getString("deelgemeente");
                double garageNaamCount = rs.getDouble("COUNT");
                newHashMap.put(deelGemeenteNaam, garageNaamCount);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return newHashMap;
    }

    public HashMap getTheftYear(int year) {
        HashMap<String, Double> newHashMap = new HashMap<String, Double>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT deelgemeente, percentagediefstal FROM autodiefstal WHERE = " + year);
            while ( rs.next() ) {
                String deelGemeenteNaam = rs.getString("deelgemeente");
                double deelPercentage = rs.getDouble("percentagediefstal");
                System.out.print(deelGemeenteNaam + " ");
                System.out.println(deelPercentage);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return newHashMap;
    }
}
