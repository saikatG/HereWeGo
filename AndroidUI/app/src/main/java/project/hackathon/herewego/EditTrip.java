package project.hackathon.herewego;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Rating;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.hackathon.herewego.CustomListView.ScrollDisabledListView;
import project.hackathon.herewego.Models.CustomList;
import project.hackathon.herewego.Models.Review;

public class EditTrip extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] days_arr;
        days_arr = getResources().getStringArray(R.array.days_array);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Spinner spinner = (Spinner) findViewById(R.id.days_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                                         int arg2, long arg3) {
                                                  // TODO Auto-generated method stub
                                                  String mselection = spinner.getSelectedItem().toString();
                                                  TextView tv = (TextView) findViewById(R.id.TimingsText);
                                                  tv.setText("Open All Day on " + mselection);
                                                  RatingBar ratingBar = (RatingBar) findViewById(R.id.RatingsStars);
                                                  ratingBar.setRating(spinner.getSelectedItemPosition()% 5);
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> arg0) {
                                                  // TODO Auto-generated method stub
                                                  //
                                              }
                                          });

        ArrayList<Review> listOfReviews = new ArrayList<Review>();
        Review R1 = new Review("http://3.bp.blogspot.com/-coH7JStVgw8/TmvdyNQBWhI/AAAAAAAAGQw/V63AT0PZF9o/s1600/Funny+cartoon+faces+pictures2.jpg","Great Place",4,"Parijat1509");
        Review R2 = new Review("http://2.bp.blogspot.com/-DyWKseCweTM/TmvdwjR3U7I/AAAAAAAAGQo/MDr1dutzn7Y/s1600/Funny+cartoon+faces+pictures.jpg","Awesome Place",4,"Saikat1992");
        listOfReviews.add(R1);
        listOfReviews.add(R2);
        listOfReviews.add(R2);
        listOfReviews.add(R2);
        listOfReviews.add(R2);
        listOfReviews.add(R2);
        listOfReviews.add(R2);
        ScrollDisabledListView listView1 = (ScrollDisabledListView) findViewById(R.id.listView1);
        ArrayAdapter<Review> adapter = new ArrayAdapter<Review>(this,android.R.layout.simple_list_item_1, listOfReviews);
        CustomList adapter1 = new
                CustomList(this, R.layout.list_single, listOfReviews);
        listView1.setScrollingEnabled(false);
        listView1.setAdapter(adapter1);
        getListViewSize(listView1);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.fullScroll(ScrollView.FOCUS_UP);
        scrollView.smoothScrollTo(0,0);

        //listView1.addHeaderView((RelativeLayout)findViewById(R.id.rellayout));
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

    public static void getListViewSize(ScrollDisabledListView myListView) {
        CustomList myListAdapter = (CustomList)myListView.getAdapter();
        if (myListAdapter == null) {
            //do nothing return null
            return;
        }
        //set listAdapter in loop for getting final size
        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
        myListView.setLayoutParams(params);
        // print height of adapter on log
        Log.i("height of listItem:", String.valueOf(totalHeight));
    }
}