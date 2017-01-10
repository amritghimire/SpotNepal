package com.amrit.spotnepal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.amrit.spotnepal.MainActivity.sptC;

public class descriptionOfPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String name=getIntent().getStringExtra("name");
        final spot sp=sptC.getSpot(name);

        String description=getIntent().getStringExtra("description");
        setContentView(R.layout.activity_description_of_place);
        ViewPager viewPager=(ViewPager) findViewById(R.id.gallery);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

        if(name!=null) {
            ((TextView) findViewById(R.id.introplace)).setText(name);
            ((TextView)findViewById(R.id.descriptionOfPlaceInDesc)).setText(description);
            spot spt=((new spotCollection(this,this)).getSpot(name));
            if(spt==null){
                this.finish();
                super.onBackPressed();
            }else if(spt.drawables!=null){
                AndroidImageAdapter androidImageAdapter=new AndroidImageAdapter(this,spt.drawables);
                viewPager.setAdapter(androidImageAdapter);
            }else {
                AndroidImageAdapter androidImageAdapter=new AndroidImageAdapter(this,new int[]{R.drawable.begnas,R.drawable.screen,R.drawable.screenn});
                viewPager.setAdapter(androidImageAdapter);

            }
        }
        else
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
               double latitude=sp.latitude;
                double longitude=sp.longitude;

                String label = name;
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
    }

}
