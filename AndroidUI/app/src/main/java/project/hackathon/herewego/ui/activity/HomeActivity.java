package project.hackathon.herewego.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import java.util.Random;

import project.hackathon.herewego.R;
import project.hackathon.herewego.Models.Destination;
import project.hackathon.herewego.Models.HWGSharedPreferences;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HWGSharedPreferences.init(this);
        Destination.initDestinationList();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.home_actionbar);
        setSupportActionBar(myToolbar);

        LinearLayout myTripsLayout = (LinearLayout) findViewById(R.id.mytrips_view);
        myTripsLayout.addView(getNewTripButton(myTripsLayout));
        myTripsLayout.addView(getView(myTripsLayout, 3));
        myTripsLayout.addView(getView(myTripsLayout, 3));
        myTripsLayout.addView(getView(myTripsLayout, 3));
        myTripsLayout.addView(getView(myTripsLayout, 3));

        LinearLayout suggestedTripsLayout = (LinearLayout) findViewById(R.id.suggestedtrips_view);
        suggestedTripsLayout.addView(getSuggestedDestination(suggestedTripsLayout, 0));
        suggestedTripsLayout.addView(getSuggestedDestination(suggestedTripsLayout, 1));
        suggestedTripsLayout.addView(getSuggestedDestination(suggestedTripsLayout, 2));
        suggestedTripsLayout.addView(getSuggestedDestination(suggestedTripsLayout, 3));

        LinearLayout bookmarkedTripsView = (LinearLayout) findViewById(R.id.bookmarkedtrips_view);
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.launchFeed);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchFeed(view);
            }
        });
    }

    public FrameLayout getView(LinearLayout container, int stackSize) {
        int[] imageList = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
                R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9,
                R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14,
                R.drawable.image15};
        FrameLayout imageStack = new FrameLayout(container.getContext());
        FrameLayout.LayoutParams imageStackLP = new FrameLayout.LayoutParams(500 + (stackSize * 50), 300 + (stackSize * 50));

        imageStack.setLayoutParams(imageStackLP);
        for(int i = 0; i < stackSize; i++) {
            FrameLayout stackItem = new FrameLayout(container.getContext());

            Random r = new Random();
            int imageIndex = r.nextInt(15);

            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(imageList[imageIndex]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);

            stackItem.addView(imageView);
            FrameLayout.LayoutParams stackItemLP = new FrameLayout.LayoutParams(500, 300);
            stackItemLP.leftMargin = 50 * (i + 1);
            stackItemLP.topMargin = 50 * (i + 1);
            stackItem.setLayoutParams(stackItemLP);

            if(i == stackSize - 1){
                TextView textView = new TextView(container.getContext());
                textView.setText("Trip Name");
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);

                stackItem.addView(textView);
            }
            imageStack.addView(stackItem);
        }
        imageStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTrip(v);
            }
        });
        return imageStack;
    }

    public FrameLayout getNewTripButton(LinearLayout container) {
        FrameLayout imageStack = new FrameLayout(container.getContext());
        FrameLayout.LayoutParams imageStackLP = new FrameLayout.LayoutParams(500, 500);
        imageStack.setLayoutParams(imageStackLP);


        FrameLayout stackItem = new FrameLayout(container.getContext());

        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(R.drawable.plusicon);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(250, 250);
        imageView.setLayoutParams(layoutParams);


        stackItem.addView(imageView);
        FrameLayout.LayoutParams stackItemLP = new FrameLayout.LayoutParams(250, 300);
        stackItemLP.leftMargin = 150;
        stackItemLP.topMargin = 100;
        stackItem.setLayoutParams(stackItemLP);


        TextView textView = new TextView(container.getContext());
        textView.setText("Create a New Trip");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(15);
        textView.setGravity(Gravity.BOTTOM);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        stackItem.addView(textView);
        imageStack.addView(stackItem);

        imageStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewTrip(v);
            }
        });
        return imageStack;
    }

    public void editTrip(View view) {
        Intent intent = new Intent(this, EditTrip.class);
        startActivity(intent);
    }

    public void launchFeed(View view) {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
    }

    public void createNewTrip(View view) {
        Intent intent = new Intent(this, DestinationSelectionActivity.class);
        startActivity(intent);
    }

    public void createNewTripStageTwo(View view) {
        Intent intent = new Intent(this, DateSelectionActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public FrameLayout getSuggestedDestination(LinearLayout container, int index) {
        int[] imageList = new int[]{R.drawable.amsterdam, R.drawable.goa, R.drawable.hyderabad, R.drawable.paris};
        String[] destinationName = new String[]{"Amsterdam", "Goa", "Hyderabad", "Paris"};
        int stackSize = 1;
        FrameLayout imageStack = new FrameLayout(container.getContext());
        FrameLayout.LayoutParams imageStackLP = new FrameLayout.LayoutParams(500 + (stackSize * 50), 300 + (stackSize * 50));

        imageStack.setLayoutParams(imageStackLP);
        for(int i = 0; i < stackSize; i++) {
            FrameLayout stackItem = new FrameLayout(container.getContext());

            Random r = new Random();

            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(imageList[index]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);

            stackItem.addView(imageView);
            FrameLayout.LayoutParams stackItemLP = new FrameLayout.LayoutParams(500, 300);
            stackItemLP.leftMargin = 50 * (i + 1);
            stackItemLP.topMargin = 50 * (i + 1);
            stackItem.setLayoutParams(stackItemLP);

            if(i == stackSize - 1){
                TextView textView = new TextView(container.getContext());
                textView.setText(destinationName[index]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(25);

                stackItem.addView(textView);
            }
            imageStack.addView(stackItem);
        }
        imageStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewTripStageTwo(v);
            }
        });
        return imageStack;
    }
}
