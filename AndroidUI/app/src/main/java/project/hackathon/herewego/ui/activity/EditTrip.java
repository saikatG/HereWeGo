package project.hackathon.herewego.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loopj.android.http.*;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.common.collect.HashBasedTable;
import com.mikepenz.itemanimators.ScaleUpAnimator;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.Duration;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import cz.msebera.android.httpclient.Header;
import project.hackathon.herewego.Adapters.RecyclerViewAdaptor;
import project.hackathon.herewego.Dividers.DividerItemDecoration;
import project.hackathon.herewego.Models.Location;
import project.hackathon.herewego.Models.LocationsManager;
import project.hackathon.herewego.Models.Review;
import project.hackathon.herewego.Models.Trip;
import project.hackathon.herewego.Models.TripType;
import project.hackathon.herewego.Models.TripsManager;
import project.hackathon.herewego.Models.Tuple;
import project.hackathon.herewego.R;
import project.hackathon.herewego.RenderMap.MapsActivity;

public class EditTrip extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onResume(){
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_edittrip);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.abc_list_divider_mtrl_alpha)));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getTrip();

        Intent intent = getIntent();
        Bundle b = intent.getExtras() != null ? intent.getExtras():null;
        final TripType tripType ;
        if( b!=null && b.containsKey("triptype")){
        tripType =  (TripType)b.getSerializable("triptype");
        }
        else{
            tripType = TripType.EDIT_TRIP;
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdaptor(LocationsManager.getInstance().getChosenLocations(), tripType);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(getResources().getDrawable(R.drawable.dottedseparator, getTheme()));
       // mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setItemAnimator(new ScaleUpAnimator());

        //RecyclerViewAdaptor listViewAdaptor = new RecyclerViewAdaptor(this, LocationsManager.getInstance().getChosenLocations());

        FloatingActionButton addLocationButton = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton mapButton = (FloatingActionButton) findViewById(R.id.mapLink);
        final FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.saveButton);
        if(tripType == TripType.EDIT_TRIP) {
            saveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // create a new trip
                    //int id = TripsManager.createTrip(LocationsManager.getInstance().getItinerary());
                    Intent intent1 = new Intent(v.getContext(), EditTrip.class);
                    intent1.putExtra("triptype", TripType.SAVED_TRIP);
                    startActivity(intent1);

                    //intent1.putExtra("tripno", id);
                }
            });
        }
        else{
            addLocationButton.setImageResource(R.drawable.ic_mode_edit_white_24dp);
            saveButton.setVisibility(View.INVISIBLE);
        }


        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tripType == TripType.EDIT_TRIP){
                    Intent intent = new Intent(view.getContext(), AddLocation.class);
                    //intent.putExtra("MoreLocations", LocationsManager.getInstance().getUnselectedLocations());
                    startActivity(intent);}
                else{
                    Intent intent1 = new Intent(view.getContext(), EditTrip.class);
                    intent1.putExtra("triptype",TripType.EDIT_TRIP);
                    startActivity(intent1);
                }
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("Locations", LocationsManager.getInstance().getChosenLocations());
                startActivity(intent);
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_trip, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EditTrip Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://project.hackathon.herewego/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EditTrip Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://project.hackathon.herewego/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public static void getDistance(double lat1, double lon1, double lat2, double lon2, final TextView view) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.add("origin",lat1+","+lon1);
        requestParams.add("destination",lat2+","+lon2);
        requestParams.add("sensor","false");
        requestParams.add("units","metric");
        client.get("http://maps.google.com/maps/api/directions/xml", requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("abc","abc");
                String response = new String(responseBody);

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;
                try
                {
                    builder = factory.newDocumentBuilder();
                    Document document = builder.parse( new InputSource( new StringReader( response ) ) );
                    XPath xPath = XPathFactory.newInstance().newXPath();
                    NodeList nodes = (NodeList)xPath.evaluate("//distance/value",
                            document.getDocumentElement(), XPathConstants.NODESET);
                    int totalDist = 0;
                    for(int i = 0 ; i<nodes.getLength();i++){
                        totalDist+=Integer.parseInt(nodes.item(i).getFirstChild().getNodeValue());
                    }

                    NodeList nodes2 = (NodeList)xPath.evaluate("//duration/value",
                            document.getDocumentElement(), XPathConstants.NODESET);
                    int totalTime = 0 ;
                    for(int i=0;i<nodes2.getLength();i++){
                        totalTime+=Integer.parseInt(nodes2.item(i).getFirstChild().getNodeValue());
                    }
                    String timeString="";
                    if(totalTime < 60){
                        timeString=totalTime+" s ";
                    }
                    else if (totalTime < 3600){
                        timeString=(int)totalTime/60 + "m "+totalTime%60;
                    }
                    else{
                        timeString=(int)totalTime/3600 +"h "+(int)((totalTime%3600)/60)+"m ";
                    }

                    view.setText(" ---------- "+String.valueOf(totalDist/1000)+" km ---- "+timeString+" ---------- ");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

}
