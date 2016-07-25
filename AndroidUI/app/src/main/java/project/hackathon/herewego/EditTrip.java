package project.hackathon.herewego;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.common.collect.HashBasedTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import project.hackathon.herewego.Adaptors.ListViewAdaptor;
import project.hackathon.herewego.Models.Location;
import project.hackathon.herewego.Models.Trip;

public class EditTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Trip trip = getTrip();
        ListView listView = (ListView)findViewById(R.id.listview);
        ListViewAdaptor listViewAdaptor = new ListViewAdaptor(this, trip.getLocations());
        listView.setAdapter(listViewAdaptor);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private Trip getTrip(){
        // this will ideally be coming from the previous activity(Intent)
        ArrayList<Location> triplocations = new ArrayList<Location>();
        Location l1 = new Location("l1","Golconda", 17.3833, 78.4011, 4.5, "fort", true, "http://3.bp.blogspot.com/-y62A9HnXRr4/U7f8wnsQFeI/AAAAAAAABq4/bxFT15Bc1Xk/s1600/Golconda-Fort.jpg",1);
        Location l2 = new Location("l2","Golconda", 17.3833, 78.4011, 4.5, "fort", true, "http://3.bp.blogspot.com/-y62A9HnXRr4/U7f8wnsQFeI/AAAAAAAABq4/bxFT15Bc1Xk/s1600/Golconda-Fort.jpg",1);
        Location l3 = new Location("l3","Golconda", 17.3833, 78.4011, 4.5, "fort", true, "http://3.bp.blogspot.com/-y62A9HnXRr4/U7f8wnsQFeI/AAAAAAAABq4/bxFT15Bc1Xk/s1600/Golconda-Fort.jpg",1);
        triplocations.add(l1);
        triplocations.add(l2);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);
        triplocations.add(l3);

        //triplocations.add()
        HashBasedTable<String,String,Double> distanceMap = HashBasedTable.create();
        //populating distance
        distanceMap.put("l1","l2",5.0);
        distanceMap.put("l2","l3",5.0);
        distanceMap.put("l3","l1",5.0);
        distanceMap.put("l1","l3",5.0);
        distanceMap.put("l3","l2",5.0);
        distanceMap.put("l2","l1",5.0);

        Trip trip = new Trip("trip1",triplocations, distanceMap);
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
}
