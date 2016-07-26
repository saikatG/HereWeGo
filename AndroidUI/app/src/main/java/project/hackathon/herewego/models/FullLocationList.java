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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kabaska on 26-Jul-16.
 */
public class FullLocationList {
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
            String yourURL = "http://triphack-api.azurewebsites.net/api/triphack/getalllocations?destinationid="+args[0];
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
                    Location loc = new Location((String)location.get("Id"), (String)location.get("Name"));
                    locationList.add(loc);
                } catch (Exception e) {
                    continue;
                }

            }
            destinationLocationsMap.put(tempDestination, locationList);
        }

    }
}
