package Database;

import Database.ConnectDatabase;
import Graphs.PieGraph;

import java.sql.Connection;

/**
 * Created by aaron on 11-4-2017.
 */
public class Runnable {
    public static void run1() {
        ConnectDatabase mainDataBase = new ConnectDatabase();
        mainDataBase.connect();
        mainDataBase.getTheftYear(2012);
    }

    public static void main(String[] args) {
        Runnable neew = new Runnable();
        neew.run1();
    }
}

