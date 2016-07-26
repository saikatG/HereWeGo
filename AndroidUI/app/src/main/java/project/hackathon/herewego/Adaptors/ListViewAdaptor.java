package project.hackathon.herewego.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import project.hackathon.herewego.models.Location;
import project.hackathon.herewego.R;

/**
 * Created by saikat on 7/24/2016.
 */
public class ListViewAdaptor extends ArrayAdapter<Location> {
    private final Context context;
    private final ArrayList<Location> values;

    public ListViewAdaptor(Context context, ArrayList<Location> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.location, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        firstLine.setText(values.get(position).getName());
        secondLine.setText(values.get(position).getRating()+"★ | "+String.valueOf(values.get(position).getTime())+" hr");
        Ion.with(imageView).load(values.get(position).getImageUrl());

        ImageButton deleteButton = (ImageButton)rowView.findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(context, "test "+position, Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }
}
