package com.amrit.spotnepal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Explore extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] list=new String[]{
                        "dating",
                        "adventure",
                        "visiting",
                        "all"
                };
                setList(list[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> categories=new ArrayList<>();
        List<spot> datings=new ArrayList<>();
        List<spot> adventorous=new ArrayList<>();
        List<spot> visiting=new ArrayList<>();
        spot[] listOfSpots;
        listOfSpots=MainActivity.sptC.getListOfspot();
        if(listOfSpots==null){
            listOfSpots=((new spotCollection(this,this)).getListOfspot());
        }
        categories.add("Dating Spots");
        categories.add("Adventurous Tourism");
        categories.add("Visiting spot");
        categories.add("All");

        for(spot s:listOfSpots){
            if(s.category.equalsIgnoreCase("dating")){
                datings.add(s);
            }else if(s.category.equalsIgnoreCase("ADVENTURE TOURISM")){
                adventorous.add(s);
            }else if(s.category.equalsIgnoreCase("VISITING SPOT")){
                visiting.add(s);
            }
        }
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(dataAdapter);
        ((ListView)findViewById(R.id.dating)).setAdapter(new CostumAdapter(this, datings.toArray(new spot[datings.size()])));
        ((ListView)findViewById(R.id.adventurous)).setAdapter(new CostumAdapter(this,adventorous.toArray(new spot[adventorous.size()])));
        ((ListView)findViewById(R.id.visiting)).setAdapter(new CostumAdapter(this,visiting.toArray(new spot[visiting.size()])));
        ((ListView)findViewById(R.id.all)).setAdapter(new CostumAdapter(this,listOfSpots));
        Intent intent=getIntent();
        String userName = intent.getStringExtra("to");
        //setList(userName);
        if(userName.equalsIgnoreCase("dating")){
            spinner.setSelection(0);
        }else if(userName.equalsIgnoreCase("adventure")){
            spinner.setSelection(1);
        }else if(userName.equalsIgnoreCase("visiting")){
            spinner.setSelection(2);
        }else{
            spinner.setSelection(3);
        }
            // and get whatever type user account id is

    }

    private void setList(String place) {
        ListView dating=(ListView) findViewById(R.id.dating);
        ListView adventure=(ListView) findViewById(R.id.adventurous);
        ListView visiting=(ListView) findViewById(R.id.visiting);
        ListView all=(ListView)findViewById(R.id.all);
        dating.setVisibility(View.INVISIBLE);
        adventure.setVisibility(View.INVISIBLE);
        visiting.setVisibility(View.INVISIBLE);
        all.setVisibility(View.INVISIBLE);
        if(place.equalsIgnoreCase("dating")){
            dating.setVisibility(View.VISIBLE);
        }else if(place.equalsIgnoreCase("adventure")){
            adventure.setVisibility(View.VISIBLE);
        }else if(place.equalsIgnoreCase("visiting")){
            visiting.setVisibility(View.VISIBLE);
        }else {
            all.setVisibility(View.VISIBLE);
        }
    }
}
