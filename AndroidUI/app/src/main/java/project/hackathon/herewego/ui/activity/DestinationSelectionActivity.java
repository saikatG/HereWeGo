package project.hackathon.herewego.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import project.hackathon.herewego.R;
import project.hackathon.herewego.models.Destination;
import project.hackathon.herewego.models.FullLocationList;
import project.hackathon.herewego.models.HWGSharedPreferences;
import project.hackathon.herewego.models.PartialLocationList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static android.R.layout.simple_list_item_1;

public class DestinationSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_selection);
        HWGSharedPreferences.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<String> destinationValuesList = new ArrayList<String>();
        destinationValuesList = Arrays.asList(Destination.getDestinationList());

        final ListView destinationListView = (ListView) findViewById(R.id.destinationList_view);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, destinationValuesList);
        destinationListView.setAdapter(adapter);
        destinationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selected = adapter.getItem(position);
                launchSelectDateActivity(selected);
            }
        });

        EditText destinationSearchEdit = (EditText) findViewById(R.id.destinationSearchEdit_text);
        destinationSearchEdit.addTextChangedListener(new TextWatcher() {


            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);

            }


            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }


            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void launchSelectDateActivity(String destination) {
        FullLocationList.initLocationList(destination);
//        PartialLocationList.initLocationList(destination);
        Intent intent = new Intent(this, DateSelectionActivity.class);
        intent.putExtra("destination", destination);
        startActivity(intent);
    }
}