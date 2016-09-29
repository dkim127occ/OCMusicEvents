package edu.orangecoastcollege.cs273.dkim127.ocmusicevents;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends ListActivity {

    private Context context = (Context) this;
    private ArrayList<MusicEvent> allEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the adapter which binds the ListView with the data in MusicEvent.java
        // since the data is in an array, use an array adapter

        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));
        try
        {
            allEvents = JSONLoader.loadJSONFromAsset(context);
        }
        catch (IOException exception)
        {
            Log.e("OC Music Events", "Error loading JSON data" + exception.getMessage());
        }
        setListAdapter(new MusicEventAdapter(context, R.layout.music_event_list_item_layout, allEvents));
        // ListActivity will inflate in its own way
        //setContentView(R.layout.activity_event_list);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {
        // use position to extract title and details


        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        MusicEvent clickedEvent = allEvents.get(pos);
        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String day = clickedEvent.getDay();
        String time = clickedEvent.getTime();
        String location = clickedEvent.getLocation();
        String addr1 = clickedEvent.getAddress1();
        String addr2 = clickedEvent.getAddress2();

        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("Date", date);
        detailsIntent.putExtra("Day", day);
        detailsIntent.putExtra("Time", time);
        detailsIntent.putExtra("Location", location);
        detailsIntent.putExtra("Address1", addr1);
        detailsIntent.putExtra("Address2", addr2);

        startActivity(detailsIntent);
    }
}
