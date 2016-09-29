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
    private TextView eventDetailTextView;
    private ImageView eventImageView;
    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDetailTextView = (TextView) findViewById(R.id.eventDetailsTextView);
        eventImageView = (ImageView) findViewById(R.id.eventImageView);

        String imageFileName = intent.getStringExtra("Title").replace(" ", "") + ".jpeg";

        eventTitleTextView.setText(intent.getStringExtra("Title"));
        eventDetailTextView.setText(intent.getStringExtra("Detail"));

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
