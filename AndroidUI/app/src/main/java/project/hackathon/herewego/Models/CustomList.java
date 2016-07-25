package project.hackathon.herewego.Models;

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

public class CustomList extends ArrayAdapter<Review>{

    private final Activity context;
    private ArrayList<Review> listOfReviews;

    public CustomList(Activity context,
                      int resourceId, ArrayList<Review> listOfReviews) {
        super(context, resourceId, listOfReviews);
        this.context = context;
        this.listOfReviews = listOfReviews;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, false);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(listOfReviews.get(position).Comment);
        Ion.with(imageView).load(listOfReviews.get(position).UserImage);
        return rowView;
    }
}