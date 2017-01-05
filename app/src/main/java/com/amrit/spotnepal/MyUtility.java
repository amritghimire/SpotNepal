package com.amrit.spotnepal;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amrit on 6/26/16.
 */
public abstract class MyUtility {

    public static boolean addVisited(Activity activity,String favoriteItem){
        //Get previous favorite items
        String favoriteList = getStringFromPreferences(activity,null,"visited");
        // Append new Favorite item
        if(favoriteList!=null){
            if(!(favoriteList.contains(favoriteItem)))
            favoriteList = favoriteList+","+favoriteItem;
        }else{
            favoriteList = favoriteItem;
        }
        // Save in Shared Preferences
        return putStringInPreferences(activity,favoriteList,"visited");
    }
    public static void setKey(Activity activity,String key,String fav){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("bookmark",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, fav);
        editor.commit();
    }
    public static boolean addFavoriteItem(Activity activity,String favoriteItem){
        //Get previous favorite items
        String favoriteList = getStringFromPreferences(activity,null,"favorites");
        // Append new Favorite item
        if(favoriteList!=null){
            favoriteList = favoriteList+","+favoriteItem;
        }else{
            favoriteList = favoriteItem;
        }
        // Save in Shared Preferences
        return putStringInPreferences(activity,favoriteList,"favorites");
    }

    public static String[] getVisitedList(Activity activity){
        String favoriteList = getStringFromPreferences(activity,null,"visited");
        return (convertStringToArray(favoriteList));
    }public static String[] getFavoriteList(Activity activity){
        String favoriteList = getStringFromPreferences(activity,null,"favorites");
        return (convertStringToArray(favoriteList));
    }

    private static boolean putStringInPreferences(Activity activity,String nick,String key){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("bookmark",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, nick);
        editor.commit();
        return true;
    }
    private static String getStringFromPreferences(Activity activity,String defaultValue,String key){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("bookmark",Activity.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, defaultValue);
        Log.d("AMRIT","convertStringToArray"+temp);

        return temp;
    }

    private static String[] convertStringToArray(String str){
        if(str==null) return null;
        String[] arr = str.split(",");
        return arr;
    }
}