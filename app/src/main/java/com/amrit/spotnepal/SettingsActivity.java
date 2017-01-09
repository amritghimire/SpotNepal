package com.amrit.spotnepal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SettingsActivity extends AppCompatActivity {
    private String[] visited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (Exception e) {
            //do nothing
        }
        visited = new String[]{"You have nothing on watched yet", "Tap on visited button to add any place to plan", "After that swipe on visited to remove the place from this list"};
        String[] favorites = MyUtility.getVisitedList(this);
        if (favorites != null) visited = Arrays.copyOf(favorites, favorites.length);
        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(visited));
        final ArrayAdapter<String> planAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lst);
        ((ListView) findViewById(R.id.visitedPlace)).setAdapter(planAdapter);
        ListView listView = (ListView) findViewById(R.id.visitedPlace);
        MainActivity.setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(SettingsActivity.this,descriptionOfPlace.class);
                intent.putExtra("name",visited[i]);
                String longDescription;
                longDescription=MainActivity.sptC.getSpot(visited[i]).longDescription;
                intent.putExtra("description",longDescription);

                startActivity(intent);
            }
        });
        SwipeListViewTouchListener touchListener =
                new SwipeListViewTouchListener(listView, new SwipeListViewTouchListener.OnSwipeCallback() {


                    @Override
                    public void onSwipeLeft(ListView listView, int[] reverseSortedPositions) {
                        onSwipe(listView,reverseSortedPositions);
                    }

                    @Override
                    public void onSwipeRight(ListView listView, int[] reverseSortedPositions) {
                        onSwipe(listView,reverseSortedPositions);
                    }
                },true,true);
        listView.setOnTouchListener(touchListener);
// Setting this scroll listener is required to ensure that during ListView scrolling,
// we don't look for swipes.
        listView.setOnScrollListener(touchListener.makeScrollListener());
    }
    private void onSwipe(ListView listView,int[] reverseSortedPositions){
        String[] bkm = MyUtility.getVisitedList(MainActivity.sptC.getActivity());
        Toast.makeText(MainActivity.sptC.getContext(), "Removed " + visited[reverseSortedPositions[0]], Toast.LENGTH_LONG).show();
        String abc = "none";
        if (bkm == null) {
            SharedPreferences sharedPreferences = MainActivity.sptC.getActivity().getSharedPreferences("bookmark", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("visited");
            editor.apply();
            recreate();
            return;
        }
        for (String a : bkm) {
            if (!(visited[reverseSortedPositions[0]].equalsIgnoreCase(a))) {
                if (abc.equals("none")) {
                    abc = a;
                } else {
                    abc += "," + a;
                }
            }
        }
        if (abc.equals("none")) {
            SharedPreferences sharedPreferences = MainActivity.sptC.getActivity().getSharedPreferences("bookmark", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("visited");
            editor.apply();
            recreate();
            return;
        }
        MyUtility.setKey(MainActivity.sptC.getActivity(), "visited", abc);
        recreate();

    }

}
