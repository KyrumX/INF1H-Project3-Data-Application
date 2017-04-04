
import jdk.nashorn.api.scripting.URLReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Selim on 4/4/2017.
 */
 
public class Read {
    public double latitude;
    public double longitude;
    public String name;
    public Read(){
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("C:/Users/Selim/Documents/read1.json")); //verander dit naar de locatie van je json file

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray parkingfacilities = (JSONArray) jsonObject.get("parkingFacilities");
            JSONObject child = (JSONObject) parkingfacilities.get(0);
            JSONObject location = (JSONObject) child.get("locationForDisplay");
            double latitude = (double) location.get("latitude");
            this.latitude = latitude;
            double longitude = (double) location.get("longitude");
            this.longitude = longitude;
            String name = child.get("name").toString();
            this.name = name;

        } catch (FileNotFoundException e) {
                // handle file not found
        } catch (IOException e) {
                // handle ioexception
        } catch (ParseException e) {
                // handle ioexception
        }
    }
    public String getName() {
        return name;
    }
    public double getLatitude(){
        return latitude;
    }
    public double getLongitude(){
        return longitude;
    }
}
