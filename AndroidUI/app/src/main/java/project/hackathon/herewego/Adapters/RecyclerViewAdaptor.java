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

import project.hackathon.herewego.Models.Location;
import project.hackathon.herewego.Models.LocationsManager;
import project.hackathon.herewego.R;
import project.hackathon.herewego.ui.activity.EntityPage;

/**
 * Created by saikat on 7/24/2016.
 */
public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {
    private ArrayList<Location> mDataset;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdaptor(ArrayList<Location> myDataset) {
        mDataset = myDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        View v;
        Location curLocation;
        Context parentContext;

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
        public RecyclerViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            this.layoutInflater = LayoutInflater.from(parent.getContext());
            ViewHolder vh = new ViewHolder(this.layoutInflater.inflate(R.layout.location_edittrip, parent, false));
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            View rowView = holder.v;
            holder.setItem(mDataset.get(position));

            TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
            TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            final ArrayList<Location> curLocs = LocationsManager.getInstance().getChosenLocations();
            firstLine.setText(curLocs.get(position).getName());
            secondLine.setText(curLocs.get(position).getRating()+"★ | "+String.valueOf(curLocs.get(position).getTime())+" hr");
            Ion.with(imageView).load(curLocs.get(position).getImageUrl());


            ImageButton deleteButton = (ImageButton)rowView.findViewById(R.id.deletebutton);
            deleteButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String name = mDataset.get(position).getName();
                    LocationsManager.getInstance().removeLocation(position,true);
                    notifyDataSetChanged();

                    Toast.makeText(v.getContext(),  name+" removed!", Toast.LENGTH_SHORT).show();

                    //parent.removeViewAt(position);
                }
                //Toast.makeText(context, "test "+position, Toast.LENGTH_SHORT).show();
            });

            ImageButton upbutton = (ImageButton)rowView.findViewById(R.id.upbutton);
            ImageButton downbutton = (ImageButton) rowView.findViewById(R.id.downbutton);

            upbutton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(position != 0){
                        Location prevLoc = LocationsManager.getInstance().getChosenLocations().get(position-1);
                        LocationsManager.getInstance().getChosenLocations().set(position-1,LocationsManager.getInstance().getChosenLocations().get(position));
                        LocationsManager.getInstance().getChosenLocations().set(position, prevLoc);
                        notifyDataSetChanged();
                    }
                }
            });

            downbutton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(position != LocationsManager.getInstance().getChosenLocations().size() - 1 ){
                        Location nextLoc = LocationsManager.getInstance().getChosenLocations().get(position + 1);
                        LocationsManager.getInstance().getChosenLocations().set(position + 1,LocationsManager.getInstance().getChosenLocations().get(position));
                        LocationsManager.getInstance().getChosenLocations().set(position, nextLoc);
                        notifyDataSetChanged();
                    }
                }
            });

            if(position == getItemCount() - 1){
                RecyclerView.LayoutParams layoutParams =  (RecyclerView.LayoutParams)rowView.getLayoutParams();
                layoutParams.bottomMargin = 10;

            }
        }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
