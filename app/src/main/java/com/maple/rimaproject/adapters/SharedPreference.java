package com.maple.rimaproject.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.maple.rimaproject.Retrofit.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";
    public static final String PROJECT = "Projects";
    String key_name;


    public SharedPreference(String key_name) {
        super();
        this.key_name = key_name;
    }

    // This four methods are used for maintaining favorites.
    public void saveArrayList(Context context, List<Project> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(key_name, jsonFavorites);

        editor.commit();
    }

    public void addArrayList(Context context, Project product) {
        List<Project> favorites = getArrayList(context);
        if (favorites == null)
            favorites = new ArrayList<Project>();
        favorites.add(product);
        saveArrayList(context, favorites);
    }

    public void removeArrayList(Context context, Project product) {
        ArrayList<Project> favorites = getArrayList(context);
        if (favorites != null) {
            favorites.remove(product);
            saveArrayList(context, favorites);
        }
    }

    public ArrayList<Project> getArrayList(Context context) {
        SharedPreferences settings;
        List<Project> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(key_name)) {
            String jsonFavorites = settings.getString(key_name, null);
            Gson gson = new Gson();
            Project[] favoriteItems = gson.fromJson(jsonFavorites,
                    Project[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Project>(favorites);
        } else
            return null;

        return (ArrayList<Project>) favorites;
    }
}