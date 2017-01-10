package com.amrit.spotnepal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public  GoogleApiClient mGoogleApiClient;

    public static spotCollection sptC;
    static int  locationAccessed;
    private Location mLastLocation;
    private String[] plans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationAccessed = 0;
// Checking for first time launch - before calling setContentView()

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        setContentView(R.layout.activity_main);
        sptC = new spotCollection(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (mGoogleApiClient == null) {
            // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(AppIndex.API).build();
        }
        plans = new String[]{"You have nothing on bookmarks yet", "Tap on bookmark button to add any place here", "Swipe the bookmark to remove"};
        String[] favorites = MyUtility.getFavoriteList(this);
        if (favorites != null) plans = Arrays.copyOf(favorites, favorites.length);
        ArrayList<String> lst = new ArrayList<>(Arrays.asList(plans));
        final ArrayAdapter<String> planAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lst);
        ((ListView) findViewById(R.id.listView)).setAdapter(planAdapter);

        setListViewHeightBasedOnChildren((ListView) findViewById(R.id.listView));
        findViewById(R.id.bookmark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookmark();
            }
        });
        findViewById(R.id.visited).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtility.addVisited(sptC.getActivity(), sptC.getCurrentName());
                sptC.getListOfspot()[sptC.getInteger(sptC.getCurrentName())].setVisited(true);
                Toast.makeText(sptC.getContext(), sptC.getCurrentName() + "marked as visited.", Toast.LENGTH_LONG).show();

                int nxt = sptC.next(sptC.getCurrent());
                setImage(nxt);
            }
        });
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_SEND);


                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sptC.getCurrent());

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }
                if (success) {
                    File f = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal"
                            + File.separator + getResources().getResourceEntryName(sptC.getCurrent()) + ".png");
                    if (!f.exists()) {
                        try {
                            Toast.makeText(MainActivity.this, "Copying file to your storage", Toast.LENGTH_LONG).show();
                            f.createNewFile();
                            FileOutputStream fo = new FileOutputStream(f);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    Uri uri = Uri.fromFile(f);
                    share.setType("image/jpg");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, "Shared from SPOTNEPAL");

                    startActivity(Intent.createChooser(share, "Share Image!"));
                }
            }
        });
        ImageButton imageView = (ImageButton) findViewById(R.id.iplace);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(sptC.getContext(),descriptionOfPlace.class);
                i.putExtra("name",sptC.getCurrentName());
                i.putExtra("description",sptC.getSpot(sptC.getCurrentName()).longDescription);
                startActivity(i);
            }
        });
        ((Button) findViewById(R.id.explore)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Explore.class);
                intent.putExtra("to","all");
                startActivity(intent);
            }
        });
        ((Button)findViewById(R.id.collection)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.sptC.getContext(),Collection.class);
                startActivity(intent);
            }
        });
        imageView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeRight() {
                forward(null);
            }

            public void onSwipeLeft() {
                prev(null);
            }

            public void onTap() {
                Toast.makeText(sptC.getContext(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });

        updateVisited(this);
        setImage(0);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i=new Intent(sptC.getContext(),descriptionOfPlace.class);
                i.putExtra("name",plans[position]);
                startActivity(i);
            }
        });
        SwipeListViewTouchListener touchListener =
                new SwipeListViewTouchListener(listView, new SwipeListViewTouchListener.OnSwipeCallback() {
                    @Override
                    public void onSwipeLeft(ListView listView, int[] reverseSortedPositions) {
                        Log.i(this.getClass().getName(), "swipe left : pos=" + reverseSortedPositions[0]);
                        String[] bkm = MyUtility.getFavoriteList(sptC.getActivity());
                        Toast.makeText(sptC.getContext(), "Removed " + plans[reverseSortedPositions[0]], Toast.LENGTH_LONG).show();
                        String abc = "none";
                        if (bkm == null) {
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("bookmark", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("favorites");
                            editor.apply();
                            recreate();
                            return;
                        }
                        for (String a : bkm) {
                            if (!(plans[reverseSortedPositions[0]].equalsIgnoreCase(a))) {
                                if (abc.equals("none")) {
                                    abc = a;
                                } else {
                                    abc += "," + a;
                                }
                            }
                        }
                        if (abc.equals("none")) {
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("bookmark", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("favorites");
                            editor.apply();
                            recreate();
                            return;
                        }
                        MyUtility.setKey(sptC.getActivity(), "favorites", abc);
                        recreate();

                    }

                    @Override
                    public void onSwipeRight(ListView listView, int[] reverseSortedPositions) {
                        //  Log.i(ProfileMenuActivity.class.getClass().getName(), "swipe right : pos="+reverseSortedPositions[0]);
                        Log.i(this.getClass().getName(), "swipe left : pos=" + reverseSortedPositions[0]);
                        String[] bkm = MyUtility.getFavoriteList(sptC.getActivity());
                        Toast.makeText(sptC.getContext(), "Removed " + plans[reverseSortedPositions[0]], Toast.LENGTH_LONG).show();
                        String abc = "none";
                        if (bkm == null) {
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("bookmark", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("favorites");
                            editor.apply();
                            recreate();
                            return;
                        }
                        for (String a : bkm) {
                            if (!(plans[reverseSortedPositions[0]].equalsIgnoreCase(a))) {
                                if (abc.equals("none")) {
                                    abc = a;
                                } else {
                                    abc += "," + a;
                                }
                            }
                        }
                        if (abc.equals("none")) {
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("bookmark", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("favorites");
                            editor.apply();
                            recreate();
                            return;
                        }
                        MyUtility.setKey(sptC.getActivity(), "favorites", abc);
                        recreate();

                    }
                },
                        true, // example : left action = dismiss
                        false); // example : right action without dismiss animation
        listView.setOnTouchListener(touchListener);
// Setting this scroll listener is required to ensure that during ListView scrolling,
// we don't look for swipes.
        listView.setOnScrollListener(touchListener.makeScrollListener());


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    finish();
                    startActivity(getIntent());
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    Toast.makeText(this,"Sorry to know that you denied the permission.We dont use permission for any privacy related tasks.You can allow permission in next launch.",Toast.LENGTH_LONG).show();                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateVisited(Activity mainActivity) {
        String[] visited=MyUtility.getVisitedList(mainActivity);
        if(visited==null) return;
        for (int i=0;i<(sptC.getListOfspot().length);i++){
            if(Arrays.toString(visited).contains(sptC.getListOfspot()[i].name)){
                sptC.getListOfspot()[i].setVisited(true);
            }
        }
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent i=new Intent(this,Explore.class);
            i.putExtra("to","visiting");
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i=new Intent(this,Explore.class);
            i.putExtra("to","adventure");
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i=new Intent(this,Explore.class);
            i.putExtra("to","dating");
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            Intent i=new Intent(this,Explore.class);
            i.putExtra("to","all");
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent share = new Intent(Intent.ACTION_SEND);


            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sptC.getCurrent());

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal");
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdir();
            }
            if (success) {
                File f = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal"
                        + File.separator + getResources().getResourceEntryName(sptC.getCurrent()) + ".png");
                if (!f.exists()) {
                    try {
                        Toast.makeText(MainActivity.this, "Copying file to your storage", Toast.LENGTH_LONG).show();
                        f.createNewFile();
                        FileOutputStream fo = new FileOutputStream(f);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                Uri uri = Uri.fromFile(f);
                share.setType("image/jpg");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                share.putExtra(Intent.EXTRA_TEXT, "Shared from SPOTNEPAL");

                startActivity(Intent.createChooser(share, "Share Image!"));
            }
        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(this,about.class);
            startActivity(intent);
        }else if(id==R.id.intro){
            Intent wc=new Intent(this,WelcomeActivity.class);
            wc.putExtra("from","home");
            startActivity(wc);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Main Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.amrit.spotnepal/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Main Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.amrit.spotnepal/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            Toast.makeText(this,"No permission granted ",Toast.LENGTH_LONG);
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            if (!Geocoder.isPresent()) {
                locationAccessed=0;
                Toast.makeText(this, R.string.no_geocoder_available,
                        Toast.LENGTH_LONG).show();
                return;
            }
            //Toast.makeText(this, "Location recieved : Lat: " + mLastLocation.getLatitude() + "long : " + mLastLocation.getLongitude(), Toast.LENGTH_LONG).show();
            ((TextView) findViewById(R.id.tLocation)).setText(R.string.foundlocation );
            locationAccessed=1;
            //getAddressFromLocation(mLastLocation, this, new GeocoderHandler());
            (new AccessLocation()).execute();
        } else {
            ((TextView) findViewById(R.id.tLocation)).setText(R.string.turnonlocation);
            Toast.makeText(this,"You may need to provide the location permission from app settings.",Toast.LENGTH_LONG).show();
            locationAccessed=0;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




    private class AccessLocation extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
            String result = null;
            try {
                List<Address> list = geocoder.getFromLocation(
                        mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                if (list != null && list.size() > 0) {
                    Address address = list.get(0);
                    // sending back first address line and locality
                    String outputAddress = " ";
                    for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        outputAddress += " " + address.getAddressLine(i);
                    }
                    result = outputAddress;//address.getAddressLine(0) + ", " + address.getLocality();
                }
            } catch (IOException e) {

            }finally {
                if (result != null) {
                    final String finalResult = result.replaceAll("\\d*$", "");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((TextView) findViewById(R.id.tLocation)).setText(finalResult);
                        }

                    });
                }
                    spot[] listt = sptC.getListOfspot();
                    spot[] lll = listt;
                    spot first = listt[0];
                    int least = -1;
                    float[] results = new float[]{(float) 0.000};
                    if (locationAccessed == 1) {
                        for (int i = 0; i < listt.length; i++) {
                            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(), listt[i].latitude, listt[i].longitude, results);
                            float metres = results[0];
                            listt[i].tempData = metres;
                            if ((least > metres) || least == -1) {
                                least = (int) metres;
                            }
                        }

                        for (int j = 0; j < listt.length; j++) {
                            for (int k = j ; k < listt.length; k++) {
                                if (listt[j].tempData > listt[k].tempData) {
                                    first = listt[j];
                                    listt[j] = listt[k];
                                    listt[k] = first;
                                }
                            }
                        }

                        sptC.setListOfspot(listt);

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setImage(0);
                    }

                });
            }
            return null;
        }
    }

    public void forward(View view){
        int nxt;
        nxt=sptC.next(sptC.getCurrent());
        if(nxt<0) setImage(0);
        else setImage(nxt);

    }

    public void prev(View view){
        int prev;
        prev=sptC.prev(sptC.getCurrent());
        if(prev<0) setImage(0);
        else setImage(prev);

    }
    public void bookmark() {
        if (sptC.getCurrent() == 0) return;
        spot[] spots = sptC.getListOfspot();
        String bookmark = spots[sptC.getCurrentId()].name;
        SharedPreferences preferences = this.getSharedPreferences("bookmark", MODE_PRIVATE);
        Log.d("AMRIT","reached here");
        final SharedPreferences.Editor editor = preferences.edit();
        String all = preferences.getString("favorites", null);
        if (all == null) {
            editor.putString("favorites", bookmark);
            editor.commit();
            editor.apply();
            Toast.makeText(this, "Added first Bookmark : " + bookmark, Toast.LENGTH_LONG).show();
            recreate();
            return;
        } else {
            Log.d("AMRIT","Bookmark: "+all.indexOf(bookmark));
            if (all.indexOf(bookmark) < 0) {
                all = all + "," + bookmark;
                editor.putString("favorites", all);
                editor.commit();
                editor.apply();
                Toast.makeText(this, "Added Bookmark : " + bookmark, Toast.LENGTH_LONG).show();
                recreate();
            } else {
                Toast.makeText(this, "Already Bookmark : " + bookmark, Toast.LENGTH_LONG).show();
            }
        }
    }
    private void setImage(int i) {
        if(sptC.getListOfspot()[i].getVisited()){
            i=sptC.next(sptC.getListOfspot()[i].drawable);
        }
        try {
            if (sptC.getListOfspot()[i].tempData == 0)
                ((TextView) findViewById(R.id.tPlace)).setText(sptC.getListOfspot()[i].name);
            else
                ((TextView) findViewById(R.id.tPlace)).setText(String.format("%s %.2f%smetres near", sptC.getListOfspot()[i].name, (sptC.getListOfspot()[i].tempData > 1000) ? sptC.getListOfspot()[i].tempData / 1000 : sptC.getListOfspot()[i].tempData, (sptC.getListOfspot()[i].tempData > 1000) ? " kilo" : " "));
            ((TextView) findViewById(R.id.tPlaceDecription)).setText(sptC.getListOfspot()[i].shortDescription);
            ((ImageView) findViewById(R.id.iplace)).setImageResource(sptC.getListOfspot()[i].drawable);
            sptC.setCurrent(sptC.getListOfspot()[i].drawable);
        }catch (Exception e){

        }
    }

}
