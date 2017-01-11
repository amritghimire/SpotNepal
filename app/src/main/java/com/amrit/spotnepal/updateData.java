package com.amrit.spotnepal;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class updateData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        File fldr = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal" + File.separator + "images");
        boolean success = true;
        if (!fldr.exists()) {
            success = fldr.mkdirs();

        }
        if (success) {
            try {
                File gpxfile = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal" + File.separator + "cities");
                if (!gpxfile.exists()) {
                    FileWriter writer = new FileWriter(gpxfile);
                    writer.append("pokhara");
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        try {
            database.setPersistenceEnabled(true);
        } catch (com.google.firebase.database.DatabaseException e) {
            //do nothing
        }
        try {
            String city = getStringFromFile("cities");
            city = city.replace('\n', ' ');
            String[] cities = city.split(",");
            for (final String city1 : cities) {
                DatabaseReference myRef = database.getReference(city1.trim().toLowerCase());
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Spots> spots = new ArrayList<Spots>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            spots.add(snapshot.getValue(Spots.class));
                        }
                        Gson gson = new Gson();
                        String json = gson.toJson(spots);
                        try {
                            File gpxfile = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal" + File.separator + city1.trim());
                                FileWriter writer = new FileWriter(gpxfile);
                                writer.write(json);
                                writer.flush();
                                writer.close();
                                Toast.makeText(updateData.this,"Use the update icon below to download missing images.We have some new places for you.",Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(String filePath) throws Exception {
        File fl = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal" + File.separator + filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

}
