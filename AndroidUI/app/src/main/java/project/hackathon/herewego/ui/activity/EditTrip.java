package project.hackathon.herewego.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.common.collect.HashBasedTable;
import com.mikepenz.itemanimators.ScaleUpAnimator;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.hackathon.herewego.Adapters.RecyclerViewAdaptor;
import project.hackathon.herewego.Dividers.DividerItemDecoration;
import project.hackathon.herewego.Models.Location;
import project.hackathon.herewego.Models.LocationsManager;
import project.hackathon.herewego.Models.Review;
import project.hackathon.herewego.Models.Trip;
import project.hackathon.herewego.Models.Tuple;
import project.hackathon.herewego.R;

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
        getTrip();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdaptor(LocationsManager.getInstance().getChosenLocations());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(getResources().getDrawable(R.drawable.dottedseparator, getTheme()));
       // mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setItemAnimator(new ScaleUpAnimator());

        //RecyclerViewAdaptor listViewAdaptor = new RecyclerViewAdaptor(this, LocationsManager.getInstance().getChosenLocations());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddLocation.class);
                //intent.putExtra("MoreLocations", LocationsManager.getInstance().getUnselectedLocations());

                startActivity(intent);

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Trip getTrip() {

        // this will ideally be coming from the previous activity(Intent)
        ArrayList<Location> triplocations = new ArrayList<Location>();
        Location l1 = new Location("l1", "Golconda1", 17.3833, 78.4011, 4.5, "fort", true, "http://3.bp.blogspot.com/-y62A9HnXRr4/U7f8wnsQFeI/AAAAAAAABq4/bxFT15Bc1Xk/s1600/Golconda-Fort.jpg", 1);
        Location l2 = new Location("l2", "SalarJung", 17.3833, 78.4011, 4.5, "fort", true, "http://hyderabadadvisor.com/wp-content/uploads/2011/05/Salar-Jung-Museum-1.jpg", 1);
        Location l3 = new Location("l3", "Hussain Sagar", 17.3833, 78.4011, 4.5, "fort", true, "https://upload.wikimedia.org/wikipedia/commons/1/14/Hussain_Sagar_lake,_Hyderabad.jpg", 1);
        Location l4 = new Location("l4", "Hussain Sagar", 17.3833, 78.4011, 4.5, "fort", true, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Mecca_Masjid_at_Hyderabad.jpg/340px-Mecca_Masjid_at_Hyderabad.jpg", 1);
        Location l5 = new Location("l5", "Charminar", 17.3833, 78.4011, 4.5, "fort", false, "https://upload.wikimedia.org/wikipedia/commons/d/d1/Charminar-Pride_of_Hyderabad.jpg", 1);
        Location l6 = new Location("l6", "Charminar", 17.3833, 78.4011, 4.5, "fort", false, "https://upload.wikimedia.org/wikipedia/en/a/a5/Falak_palace.jpg", 1);
        Review R1 = new Review("http://3.bp.blogspot.com/-coH7JStVgw8/TmvdyNQBWhI/AAAAAAAAGQw/V63AT0PZF9o/s1600/Funny+cartoon+faces+pictures2.jpg","Great Place",5,"Parijat1509", new DateTime(DateTime.now()));
        Review R2 = new Review("http://2.bp.blogspot.com/-DyWKseCweTM/TmvdwjR3U7I/AAAAAAAAGQo/MDr1dutzn7Y/s1600/Funny+cartoon+faces+pictures.jpg","Awesome Place",4,"Saikat1992",new DateTime(DateTime.now()));
        HashMap<String, List<Tuple<DateTime, DateTime>>> openSchedule = new HashMap<>();
        List<Tuple<DateTime,DateTime> > dates = new ArrayList<Tuple<DateTime, DateTime>>() ;
        Tuple<DateTime,DateTime> tuple = new Tuple<>(DateTime.now(), DateTime.now());
        dates.add(tuple);
        openSchedule.put("Monday", dates);
        openSchedule.put("Tuesday", dates);
        openSchedule.put("Wednesday", dates);
        openSchedule.put("Thursday", dates);
        openSchedule.put("Friday", dates);
        openSchedule.put("Saturday", dates);
        openSchedule.put("Sunday", dates);

        l1.setOpenSchedule(openSchedule);
        l2.setOpenSchedule(openSchedule);
        l3.setOpenSchedule(openSchedule);
        l4.setOpenSchedule(openSchedule);
        l5.setOpenSchedule(openSchedule);
        l6.setOpenSchedule(openSchedule);

        ArrayList<Review> reviews= new ArrayList<Review>();
        reviews.add(R1);
        reviews.add(R2);
        l1.setReviews(reviews);
        l2.setReviews(reviews);
        l3.setReviews(reviews);
        l4.setReviews(reviews);
        l5.setReviews(reviews);
        l6.setReviews(reviews);


        LocationsManager locationsManager = LocationsManager.getInstance();
        locationsManager.addLocation(l1, true);
        locationsManager.addLocation(l2, true);
        locationsManager.addLocation(l3, true);

        locationsManager.addLocation(l4, false);
        locationsManager.addLocation(l5, false);
        locationsManager.addLocation(l6, false);




        //triplocations.add()
        HashBasedTable<String, String, Double> distanceMap = HashBasedTable.create();
        //populating distance
        distanceMap.put("l1", "l2", 5.0);
        distanceMap.put("l2", "l3", 5.0);
        distanceMap.put("l3", "l1", 5.0);
        distanceMap.put("l1", "l3", 5.0);
        distanceMap.put("l3", "l2", 5.0);
        distanceMap.put("l2", "l1", 5.0);

        Trip trip = new Trip("trip1", triplocations, distanceMap);
        return trip;
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
}
