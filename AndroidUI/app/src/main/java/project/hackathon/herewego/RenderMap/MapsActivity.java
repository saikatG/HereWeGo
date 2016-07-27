package project.hackathon.herewego.RenderMap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.lang.reflect.Array;
import java.util.ArrayList;

import project.hackathon.herewego.R;
import project.hackathon.herewego.Models.Location;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Listener {

    private GoogleMap mMap;
    private ArrayList<Location> locs;
    public static final String TAG = "MAP_PRITAM";
    public static final float DEFAULT_ZOOM_LEVEL = 9.0f;

    ArrayList<LatLng> points = null;
    PolylineOptions lineOptions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null){
            if (intent.getExtras() != null && intent.getExtras().containsKey("Locations")) {
                final Bundle bundle = intent.getExtras();
                this.locs = (ArrayList<Location>) bundle.getSerializable("Locations");
            }
        }
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (this.locs != null && !this.locs.isEmpty()) {
            ArrayList<Marker> markers = new ArrayList<Marker>();
            Marker temp;
            LatLng[] ll = new LatLng[4];
            ll[0] = new LatLng(17.3833, 78.4011);
            ll[1] = new LatLng(17.3714, 78.4804);
            ll[2] = new LatLng(17.2543, 78.6808);
            ll[3] = new LatLng(17.2403, 78.4294);

            for (int llindex = 0; llindex < this.locs.size(); llindex++) {
                Location currentLoc = locs.get(llindex);
                String title = currentLoc.getName();
                double lat = currentLoc.getLatitude();
                double longitude = currentLoc.getLongitude();
                LatLng currLatLong = new LatLng(lat, longitude);
                LoadBitmap loadBitmap = new LoadBitmap(currentLoc.getImageUrl());
                Bitmap img = null;
                try {
                    img = loadBitmap.execute().get();
                }
                catch (Exception ex){

                }
                if (img == null){
                    temp = mMap.addMarker(new MarkerOptions().position(currLatLong).title(title));
                }
                else{
                    temp = mMap.addMarker(new MarkerOptions().position(currLatLong).title(title).icon(BitmapDescriptorFactory.fromBitmap(img)));
                }

                markers.add(temp);

                if (llindex > 0) {
                    LatLng src = new LatLng(this.locs.get(llindex-1).getLatitude(),this.locs.get(llindex-1).getLongitude());
                    LatLng dest = currLatLong;
                    String url = GetDataFromUrl.getDirectionsUrl(src, dest);
                    GetDirections getDirections = new GetDirections(MapsActivity.this);
                    getDirections.startGettingDirections(url);

                }
            }

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();

            int padding = 100;
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
                    marker.showInfoWindow();
                    return true;
                }
            });
        }
    }

    //The task for getting directions ends up here...
    @Override
    public void onSuccessfullRouteFetch(final List<List<HashMap<String, String>>> result) {

        //if it takes a long time, we will do it in a seperate thread...
        //new Thread(new Runnable() {
        //@Override
        // public void run() {

        MarkerOptions markerOptions = new MarkerOptions();
        // Traversing through all the routes
        for (List<HashMap<String, String>> path : result) {
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            int size = path.size();
            // Get all the points for this route
            for (HashMap<String, String> point : path) {
                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);
                points.add(position);
            }

            // Adding all the points in the route to LineOptions
            lineOptions.addAll(points);
            lineOptions.width(12);
            lineOptions.color(Color.RED);
            //}

            //Do all UI operations on the UI thread only...
            //runOnUiThread(new Runnable() {
            //@Override
            //  public void run() {
            // Drawing polyline in the Google Map for the this route
            mMap.addPolyline(lineOptions);
            //   }
            // });

            //}
            //}).start();

        }}

    @Override
    public void onFail() {
        Log.i(TAG, "Failed to get directions from Google...");
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public class LoadBitmap extends AsyncTask<String, Void, Bitmap> {
        private String mUrl;

        public LoadBitmap(String url) {
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            final String src = mUrl;
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
            // do stuff
        }


    }

}
