package Events;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Aaron on 16-4-2017.
 */
public class CSVReader {
    public static void main(String[] args) {

        String csvFile = "C:/Users/Aaron/Downloads/parking.csv";
        BufferedReader br = null;
        String line = "";

        //Soort seperator gebruikt in CSV file, in dit geval een ';'
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] lineSplitter = line.split(cvsSplitBy);

                //Pak het vierde en vijfde item van elke regel, de naam van de garage en de code
                System.out.println("Naam van de garage: " + lineSplitter[3] + ", de code van de garage= " + lineSplitter[4]);

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
