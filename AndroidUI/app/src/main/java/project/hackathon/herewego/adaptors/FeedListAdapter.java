package project.hackathon.herewego.adaptors;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import project.hackathon.herewego.R;
import project.hackathon.herewego.Models.FeedEntity;

public class FeedListAdapter extends ArrayAdapter<FeedEntity>{

    private final Activity context;
    private ArrayList<FeedEntity> listOfFeeds;

    public FeedListAdapter(Activity context,
                           int resourceId, ArrayList<FeedEntity> listOfFeeds) {
        super(context, resourceId, listOfFeeds);
        this.context = context;
        this.listOfFeeds = listOfFeeds;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.feed_item, null, false);
        TextView UserName = (TextView) rowView.findViewById(R.id.userName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.profile_pic);
        ImageView tripImage = (ImageView) rowView.findViewById(R.id.TripImage);
        TextView UserComment = (TextView) rowView.findViewById(R.id.userFeed);
        TextView date = (TextView) rowView.findViewById(R.id.DateOfPost);
        UserName.setText(listOfFeeds.get(position).UserName);
        Ion.with(imageView).load(listOfFeeds.get(position).UserImage);
        UserComment.setText(listOfFeeds.get(position).Comment);
        date.setText(listOfFeeds.get(position).Date);
        Ion.with(tripImage).load(listOfFeeds.get(position).TripImage);

        return rowView;
    }
}
