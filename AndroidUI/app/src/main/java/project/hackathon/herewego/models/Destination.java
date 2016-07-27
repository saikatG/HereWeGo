package project.hackathon.herewego.Models;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kabaska on 26-Jul-16.
 */
public class Destination {
    private static String[] destinationList;

    public static void initDestinationList() {
        DestinationsTask task = new DestinationsTask();
        task.execute();
    }
    public static String[] getDestinationList() {
        return destinationList;
    }

    static class DestinationsTask extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... args) {
            String yourURL = "http://triphack-api.azurewebsites.net/api/triphack/getdestinations";
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
                return parsedString;
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

        protected void onPostExecute(String parsedString) {
            parsedString = parsedString.substring(1, parsedString.length()-3);
            parsedString = parsedString.replace("\"", "");
            destinationList = parsedString.split(",");

        }

    }
}
