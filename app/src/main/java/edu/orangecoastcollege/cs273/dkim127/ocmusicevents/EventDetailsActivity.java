package edu.orangecoastcollege.cs273.dkim127.ocmusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventTitleTextView;
    private TextView eventDateDayTextView;
    private TextView eventTimeTextView;
    private TextView eventLocationTextView;
    private TextView eventAddress1TextView;
    private TextView eventAddress2TextView;

    private ImageView eventImageView;
    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDateDayTextView = (TextView) findViewById(R.id.eventDateDayTextView);
        eventTimeTextView = (TextView) findViewById(R.id.eventTimeTextView);
        eventLocationTextView = (TextView) findViewById(R.id.eventLocationTextView);
        eventAddress1TextView = (TextView) findViewById(R.id.eventAddress1TextView);
        eventAddress2TextView = (TextView) findViewById(R.id.eventAddress2TextView);

        eventImageView = (ImageView) findViewById(R.id.eventImageView);

        String imageFileName = intent.getStringExtra("Title").replace(" ", "") + ".jpeg";

        eventTitleTextView.setText(intent.getStringExtra("Title"));
        eventDateDayTextView.setText(intent.getStringExtra("Day") + ", " + intent.getStringExtra("Date"));
        eventTimeTextView.setText(intent.getStringExtra("Time"));
        eventLocationTextView.setText(intent.getStringExtra("Location"));
        eventAddress1TextView.setText(intent.getStringExtra("Address1"));
        eventAddress2TextView.setText(intent.getStringExtra("Address2"));

        // load the image from the assets folder using the AssetManager class
        AssetManager am = context.getAssets();

        try
        {
            InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, intent.getStringExtra("Title"));
            eventImageView.setImageDrawable(image);
        }
        catch (IOException exception)
        {
            Log.e("OC Music Events", "Cannot load image " + imageFileName);
        }
    }
}
