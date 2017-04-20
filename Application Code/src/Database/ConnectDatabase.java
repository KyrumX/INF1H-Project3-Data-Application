package Database;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.sql.SQLException;


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
            while (rs.next()) {
                String deelGemeenteNaam = rs.getString("deelgemeente");
                double garageNaamCount = rs.getDouble("COUNT");
                newHashMap.put(deelGemeenteNaam, garageNaamCount);
            }
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

            rs = stmt.executeQuery("SELECT deelgemeente, percentagediefstal FROM autodiefstal WHERE jaar = " + year);
            while (rs.next()) {
                String deelGemeenteNaam = rs.getString("deelgemeente");
                double deelPercentage = rs.getDouble("percentagediefstal");
                newHashMap.put(deelGemeenteNaam, deelPercentage);
            }
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return newHashMap;
    }

    public int parser(String a, float b2, float c2) {
        int updated = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = DriverManager.getConnection(url, user, password);

            String insertSQL = "INSERT INTO testparser(garagenaam, xpos, ypos) VALUES(?, ? ,?)";
            stmt = conn.prepareStatement(insertSQL);

            stmt.setString(1, a);
            stmt.setFloat(2, b2);
            stmt.setFloat(3, c2);

            System.out.println("Inserted data into the database...");
            updated = stmt.executeUpdate();



        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        this.conn = conn;
        return updated;

    }
}

//    public Connection parser(String a, float b2, float c2) {
//        Connection conn = null;
//        Statement stmt = null;
//
//        try {
//            stmt = this.conn.createStatement();
//            ResultSet rs = stmt.executeQuery("INSERT INTO testparser(garagename, xpos, ypos) VALUES(" + a + " AS " + "garagename");
//            stmt.executeUpdate(rs);
//            rs = stmt.executeQuery("INSERT INTO testparser(garagename, xpos, ypos) VALUES(" + b2 + " AS " + "xpos");
//            stmt.executeUpdate(rs);
//            rs = stmt.executeQuery("INSERT INTO testparser(garagename, xpos, ypos) VALUES(" + c2 + " AS " + "ypos");
//            stmt.executeUpdate(rs);
//            System.out.println("Inserted data into the database...");

//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null)
//                    conn.close();
//            } catch (SQLException se) {
//            }
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        System.out.println("Thank you for your service.");
//        this.conn = conn;
//        return conn;