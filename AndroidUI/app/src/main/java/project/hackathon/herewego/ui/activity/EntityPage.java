package project.hackathon.herewego.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import project.hackathon.herewego.Adapters.CustomListAdaptor;
import project.hackathon.herewego.Adapters.ImageAdapter;
import project.hackathon.herewego.Models.Location;
import project.hackathon.herewego.Models.Review;
import project.hackathon.herewego.R;
import project.hackathon.herewego.custom.components.ScrollDisabledListView;

public class EntityPage extends AppCompatActivity {

    Location location;
    Float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter_image = new ImageAdapter(getApplicationContext());
        viewPager.setAdapter(adapter_image);
        viewPager.setCurrentItem(0);

        Intent intent = getIntent();
        if(intent != null){
            Bundle b = intent.getExtras();
            if(b != null){
               this.location = (Location)b.getSerializable("location");
            }
        }
        //calculate rating
        rating=0.0f;
        for(Review review :location.getReviews()){
            rating+=review.Rating;
        }
        rating=rating/location.getReviews().size();

        final Spinner spinner = (Spinner) findViewById(R.id.days_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {
                // TODO Auto-generated method stub
                String mselection = spinner.getSelectedItem().toString();
                TextView tv = (TextView) findViewById(R.id.TimingsText);
                tv.setText(location.getScheduleTimes(mselection));
                RatingBar ratingBar = (RatingBar) findViewById(R.id.RatingsStars);
                ratingBar.setRating(spinner.getSelectedItemPosition()% 5);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ScrollDisabledListView listView1 = (ScrollDisabledListView) findViewById(R.id.listView1);

        CustomListAdaptor adapter1 = new
                CustomListAdaptor(this, R.layout.review_item, this.location.getReviews());
        listView1.setScrollingEnabled(false);
        listView1.setAdapter(adapter1);
        getListViewSize(listView1);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.fullScroll(ScrollView.FOCUS_UP);
        scrollView.smoothScrollTo(0,0);
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
        CustomListAdaptor myListAdapter = (CustomListAdaptor) myListView.getAdapter();
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
