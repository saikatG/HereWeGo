package project.hackathon.herewego.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import project.hackathon.herewego.models.Review;
import project.hackathon.herewego.R;

public class CustomListAdaptor extends ArrayAdapter<Review> {

    private final Activity context;
    private List<Review> listOfReviews;

    public CustomListAdaptor(Activity context,
                             int resourceId, List<Review> listOfReviews) {
        super(context, resourceId, listOfReviews);
        this.context = context;
        this.listOfReviews = listOfReviews;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.review_item, null, false);
        TextView UserName = (TextView) rowView.findViewById(R.id.UserName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        TextView UserComment = (TextView) rowView.findViewById(R.id.comment);
        TextView UserRating = (TextView) rowView.findViewById(R.id.rate);
        UserName.setText(listOfReviews.get(position).UserId);
        Ion.with(imageView).load("http://www.tirumalesa.com/wp-content/uploads/2015/12/King-Khan-Sharukh-Khan.jpg");
        UserComment.setText(listOfReviews.get(position).Statement);
        UserRating.setText(" Rating: " + String.valueOf(listOfReviews.get(position).Rating) +"/5");
        return rowView;
    }
}
