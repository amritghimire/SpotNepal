package com.amrit.spotnepal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrit on 1/10/17.
 */

public class Spots {
    public int id;
    public String city;
    public String drawable;
    public String drawables;
    public String name,shortDescription,longDescription;
    public double latitude,longitude;
    public double tempData;
    public boolean visited;
    public boolean planned;
    public String category;

    public Spots() {
    }

    public Spots(String city,String category, String drawable, int id, double latitude, String longDescription, double longitude, String name, boolean planned, String shortDescription, double tempData, boolean visited, String drawables) {
        this.category = category;
        this.city=city;
        this.drawable = drawable;
        this.id = id;
        this.latitude = latitude;
        this.longDescription = longDescription;
        this.longitude = longitude;
        this.name = name;
        this.planned = planned;
        this.shortDescription = shortDescription;
        this.tempData = tempData;
        this.visited = visited;
        this.drawables=drawables;
    }



}
