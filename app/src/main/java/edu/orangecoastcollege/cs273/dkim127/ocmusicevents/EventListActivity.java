package edu.orangecoastcollege.cs273.dkim127.ocmusicevents;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the adapter which binds the ListView with the data in MusicEvent.java
        // since the data is in an array, use an array adapter

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));

        // ListActivity will inflate in its own way
        //setContentView(R.layout.activity_event_list);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {
        // use position to extract title and details
        String title = MusicEvent.titles[pos];
        String details = MusicEvent.details[pos];

        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("Detail", details);

        startActivity(detailsIntent);
    }
}
