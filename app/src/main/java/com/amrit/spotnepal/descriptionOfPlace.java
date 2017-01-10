package com.amrit.spotnepal;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import static android.text.Html.FROM_HTML_OPTION_USE_CSS_COLORS;
import static com.amrit.spotnepal.MainActivity.sptC;

public class descriptionOfPlace extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    @SuppressWarnings("deprecation")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String name = getIntent().getStringExtra("name");
        final spot sp = sptC.getSpot(name);

        String description = getIntent().getStringExtra("description");
        setContentView(R.layout.activity_description_of_place);
        ViewPager viewPager = (ViewPager) findViewById(R.id.gallery);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

        if (name != null) {
            ((TextView) findViewById(R.id.introplace)).setText(name);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ((TextView) findViewById(R.id.descriptionOfPlaceInDesc)).setText(Html.fromHtml(description, FROM_HTML_OPTION_USE_CSS_COLORS));
            } else {
                ((TextView) findViewById(R.id.descriptionOfPlaceInDesc)).setText(Html.fromHtml(description));
            }
            spot spt = ((new spotCollection(this, this)).getSpot(name));
            if (spt == null) {
                this.finish();
                super.onBackPressed();
            } else if (spt.drawables != null) {
                AndroidImageAdapter androidImageAdapter = new AndroidImageAdapter(this, spt.drawables);
                viewPager.setAdapter(androidImageAdapter);
            } else {
                AndroidImageAdapter androidImageAdapter = new AndroidImageAdapter(this, new int[]{R.drawable.begnas, R.drawable.screen, R.drawable.screenn});
                viewPager.setAdapter(androidImageAdapter);
 
            }
        } else
            ((TextView) findViewById(R.id.introplace)).setText("iugkfbj vfggghkbfj \n v,msdy\tofcihkn, xysoiulhkj,cn \n" +
                    "fhjbv" +
                    "xvhvvvpoil" +
                    "vx,zvikdn" +
                    "xvujzmndfvkn");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitude = sp.latitude;
                double longitude = sp.longitude;

                String label = name;
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("descriptionOfPlace Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
