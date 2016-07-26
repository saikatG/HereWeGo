package project.hackathon.herewego.models;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kabaska on 27-Jul-16.
 */
public class PartialLocationList {
    private static Map<String, ArrayList<Location>> destinationLocationsMap;
    private static String tempDestination;

    public static void initLocationList(String destination) {
        if(destinationLocationsMap == null)
            destinationLocationsMap = new HashMap<String, ArrayList<Location>>();
        if(destinationLocationsMap.get(destination) != null)
            return;
        LocationsTask task = new LocationsTask();
        task.execute(destination);
    }
    public static ArrayList<Location> getLocationList(String destination) {
        return destinationLocationsMap.get(destination);
    }

    static class LocationsTask extends AsyncTask<String, Void, JSONArray> {

        protected JSONArray doInBackground(String... args) {
            String yourURL = "http://triphack-api.azurewebsites.net/api/triphack/getlocations?destinationid="+args[0]+"&preference=1";
            String parsedString = "";

            try {

                URL url = new URL(yourURL);
                URLConnection conn = url.openConnection();

                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                InputStream is = httpConn.getInputStream();
                parsedString = convertinputStreamToString(is);
                JSONArray locations = new JSONArray(parsedString);
                tempDestination = args[0];
                return locations;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String convertinputStreamToString(InputStream ists)
                throws IOException {
            if (ists != null) {
                StringBuilder sb = new StringBuilder();
                String line;

                try {
                    BufferedReader r1 = new BufferedReader(new InputStreamReader(
                            ists, "UTF-8"));
                    while ((line = r1.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } finally {
                    ists.close();
                }
                return sb.toString();
            } else {
                return "";
            }
        }

        protected void onPostExecute(JSONArray locations) {
            ArrayList<Location> locationList = new ArrayList<Location>();
            for(int i = 0; i < locations.length(); i++) {
                try {
                    JSONObject location = (JSONObject) locations.get(i);
                    JSONArray imageJSONArray = (JSONArray) location.get("Images");
                    String[] imagesArray = new String[imageJSONArray.length()];
                    for(int j = 0; j < imageJSONArray.length(); j++)
                        imagesArray[j] = imageJSONArray.getString(j);
                    String[] daysOfWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                    JSONObject jsonSchedule = (JSONObject)location.get("OpenSchedule");
                    LocationVisitingSchedule locationVisitingSchedule = new LocationVisitingSchedule();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    for(int j = 0; j < daysOfWeek.length; j++) {
                        JSONArray timeSlots = (JSONArray) jsonSchedule.get(daysOfWeek[j]);
                        if(timeSlots != null) {
                            JSONObject slot0 = (JSONObject) timeSlots.get(0);
                            String time1 = (String)slot0.get("m_Item1");
                            Date openingTime = format.parse(time1);
                            String time2 = (String)slot0.get("m_Item2");
                            Date closingTime = format.parse(time2);
                            Timings timings = new Timings(openingTime, closingTime);
                            locationVisitingSchedule.schedule.put(daysOfWeek[j], timings);
                        }
                    }

                    Location loc = new Location(
                            (String)location.get("Id"),
                            (String)location.get("Name"),
                            (String)location.get("Latitude"),
                            (String)location.get("Longitude"),
                            (String)location.get("AverageRating"),
                            (String)location.get("Category"),
                            imagesArray,
                            locationVisitingSchedule,
                            (String)location.get("DurationToVisit")
                    );
                    locationList.add(loc);
                } catch (Exception e) {
                    continue;
                }

            }
            destinationLocationsMap.put(tempDestination, locationList);
        }

    }
}
