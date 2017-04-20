package Events;

import Database.ConnectDatabase;
import java.io.*;

/**
 * Created by Aaron on 16-4-2017.
 */

public class CSVReader {
    public static void main(String[] args) {

        String csvFile = "C:/Users/Ryan Wilson/Downloads/parking.csv";        //Verander de naam van de User naar je eigen naam
        BufferedReader br = null;
        String line = "";
        String a = null;
        int b =  0;
        int c =  0;

        //Soort seperator gebruikt in CSV file, in dit geval een ';'
        String cvsSplitBy = ";";

        try {
            ConnectDatabase DataBase = new ConnectDatabase();
            DataBase.connect();
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] lineSplitter = line.split(cvsSplitBy);

                //Pak het vierde en vijfde item van elke regel, de naam van de garage en de code
                System.out.println("Naam van de garage: " + lineSplitter[3] + ", Longditude: " + lineSplitter[2] + ", Latitude: " + lineSplitter[1]);
                a = lineSplitter[3];
                float b2 = Float.parseFloat(lineSplitter[2]);
                float c2 = Float.parseFloat(lineSplitter[1]);

                DataBase.parser(a, b2, c2);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
