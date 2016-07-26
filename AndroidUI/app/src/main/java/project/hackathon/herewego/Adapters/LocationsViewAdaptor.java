package project.hackathon.herewego.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import project.hackathon.herewego.models.Location;
import project.hackathon.herewego.models.LocationsManager;
import project.hackathon.herewego.R;
import project.hackathon.herewego.ui.activity.EntityPage;

/**
 * Created by sadutt on 7/26/2016.
 */
public class LocationsViewAdaptor extends RecyclerView.Adapter<LocationsViewAdaptor.ViewHolder> {
    private ArrayList<Location> mDataset;
    private LayoutInflater layoutInflater;



    public LocationsViewAdaptor(ArrayList<Location> myDataset) {
        mDataset = myDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        View v;
        Context parentContext;
        Location curLocation;
        public ViewHolder(View v) {
            super(v);
            this.v=v;
            v.setOnClickListener(this);
            parentContext = v.getContext();
        }
        public void setItem(Location location) {
            curLocation = location;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), EntityPage.class);
            intent.putExtra("location", curLocation);
            parentContext.startActivity(intent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolder vh = new ViewHolder(this.layoutInflater.inflate(R.layout.location, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        View rowView = holder.v;
        holder.setItem(mDataset.get(position));

        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        final ArrayList<Location> curLocs = LocationsManager.getInstance().getUnselectedLocations();
        firstLine.setText( curLocs.get(position).getName());
        secondLine.setText(curLocs.get(position).getRating()+"★ | "+String.valueOf(curLocs.get(position).getTime())+" hr");
        Ion.with(imageView).load(curLocs.get(position).getImageUrl());
        ImageButton addButton = (ImageButton)rowView.findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = mDataset.get(position).getName();
                LocationsManager.getInstance().removeLocation(position,false);
                notifyDataSetChanged();
                Toast.makeText(v.getContext(),name+ " added :)" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
