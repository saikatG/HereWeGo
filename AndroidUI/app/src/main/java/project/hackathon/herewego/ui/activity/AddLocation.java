package project.hackathon.herewego.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import project.hackathon.herewego.Adapters.LocationsViewAdaptor;
import project.hackathon.herewego.Adapters.RecyclerViewAdaptor;
import project.hackathon.herewego.models.LocationsManager;
import project.hackathon.herewego.R;

public class AddLocation extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_addlocation);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerViewAdaptor recyclerViewAdaptor = null;

        mAdapter = new LocationsViewAdaptor(LocationsManager.getInstance().getUnselectedLocations());
        mRecyclerView.setAdapter(mAdapter);
    }
}
