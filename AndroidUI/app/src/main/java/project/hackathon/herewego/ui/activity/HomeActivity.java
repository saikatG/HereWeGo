package project.hackathon.herewego.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        myTripsLayout.addView(getView(myTripsLayout, 3));
        myTripsLayout.addView(getView(myTripsLayout, 3));
        myTripsLayout.addView(getView(myTripsLayout, 3));
        myTripsLayout.addView(getView(myTripsLayout, 3));

        LinearLayout suggestedTripsLayout = (LinearLayout) findViewById(R.id.suggestedtrips_view);
        suggestedTripsLayout.addView(getView(suggestedTripsLayout, 1));
        suggestedTripsLayout.addView(getView(suggestedTripsLayout, 1));
        suggestedTripsLayout.addView(getView(suggestedTripsLayout, 1));
        suggestedTripsLayout.addView(getView(suggestedTripsLayout, 1));

        LinearLayout bookmarkedTripsView = (LinearLayout) findViewById(R.id.bookmarkedtrips_view);
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));
        bookmarkedTripsView.addView(getView(bookmarkedTripsView, 3));
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

    public void editTrip(View view) {
        Intent intent = new Intent(this, EditTrip.class);
        startActivity(intent);
    }

    public void createNewTrip(View view) {
        Intent intent = new Intent(this, DestinationSelectionActivity.class);
        startActivity(intent);
    }
}
