package com.amrit.spotnepal;

import android.location.Location;

/**
 * Created by amrit on 6/20/16.
 */
public class spot {
    int id;
    static int count=0;
    public int drawable;
    int[] drawables;
    String name,shortDescription,longDescription;
    double latitude,longitude;
    double tempData;
    private boolean visited;
    private boolean planned;
    public String category;

    public spot(int drawable,String name,String shortDescription,double latitude,double longitude,String category,String longDescription,int[] drawables) {
        this.drawable=drawable;
        this.name=name;
        this.id=count;
        count++;
        this.shortDescription=shortDescription;
        this.latitude=latitude;
        this.longitude=longitude;
        this.visited=false;
        this.planned=false;
        this.tempData=0;
        this.longDescription=longDescription;
        this.drawables=drawables;
        this.category=category;
    }
    public spot(int drawable,String name,String shortDescription,double latitude,double longitude,String category,String description) {
        this.drawable=drawable;
        this.name=name;
        this.id=count;
        count++;
        this.shortDescription=shortDescription;
        this.latitude=latitude;
        this.longitude=longitude;
        this.visited=false;
        this.planned=false;
        this.tempData=0;
        this.longDescription=description;
        this.category=category;
        this.drawables=null;
    }

        public void setDescription(String longDescr){
        this.longDescription=longDescr;
    }
    public void setVisited(boolean visited){
        this.visited=visited;
    }
    public boolean getVisited(){
        return this.visited;
    }

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }
}
