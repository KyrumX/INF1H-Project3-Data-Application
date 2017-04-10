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
        double deelCentrum = 0;
        double deelPrins = 0;
        double deelIJssel = 0;
        double deelFeij = 0;
        double deelChar = 0;
        double deelOver = 0;
        HashMap<String, Double> newHashMap = new HashMap<String, Double>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT garagenaam, deelgemeente FROM Garages");
            while ( rs.next() ) {
                String garageNaam = rs.getString("garagenaam");
                String deelGemeente = rs.getString("deelgemeente");
//                newHashMap.put("Turkey Burger", 13);
                System.out.println(deelGemeente);
                if(deelGemeente.equals("prins alexander")) {
                    deelPrins += 1;
                }
                else if(deelGemeente.equals("stadscentrum")) {
                    deelCentrum += 1;
                }
                else if(deelGemeente.equals("charlois")) {
                    deelChar += 1;
                }
                else if(deelGemeente.equals("ijsselmonde")) {
                    deelIJssel += 1;
                }
                else if(deelGemeente.equals("overschie")) {
                    deelOver += 1;
                }
                else if(deelGemeente.equals("feijenoord")) {
                    deelFeij += 1;
                }
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        newHashMap.put("Stadscentrum", deelCentrum);
        newHashMap.put("Charlois", deelChar);
        newHashMap.put("Prins Alexnader", deelPrins);
        newHashMap.put("IJsselmonde", deelIJssel);
        newHashMap.put("Overschie", deelOver);
        newHashMap.put("Feijenoord", deelFeij);
        return newHashMap;
    }
}
