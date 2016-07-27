package project.hackathon.herewego.ui.activity;

/**
 * Created by paverm on 27-07-2016.
 */

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ListView;
        import java.util.ArrayList;

        import project.hackathon.herewego.R;
        import project.hackathon.herewego.Models.FeedEntity;
        import project.hackathon.herewego.adaptors.FeedListAdapter;
public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<FeedEntity> listOfFeeds = new ArrayList<FeedEntity>();
        FeedEntity f1 = new FeedEntity("http://www.tirumalesa.com/wp-content/uploads/2015/12/King-Khan-Sharukh-Khan.jpg","Great place - Hyderabad","SRK","07/06/2016","http://www.tata.com/images/article/worldwide-northamerica-mast.jpg");
        FeedEntity f2 = new FeedEntity("http://www.tirumalesa.com/wp-content/uploads/2015/12/King-Khan-Sharukh-Khan.jpg","Great place - Hyderabad","SRK","07/06/2016","http://www.tata.com/images/article/worldwide-northamerica-mast.jpg");
        listOfFeeds.add(f1);
        listOfFeeds.add(f2);
        //ArrayAdapter<FeedEntity> adapter = new ArrayAdapter<FeedEntity>(this,android.R.layout.simple_list_item_1, listOfFeeds);
        FeedListAdapter adapter1 = new FeedListAdapter(this, R.layout.feed_item, listOfFeeds);

        ListView lv = (ListView)findViewById(R.id.list);
        lv.setAdapter(adapter1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }
}
