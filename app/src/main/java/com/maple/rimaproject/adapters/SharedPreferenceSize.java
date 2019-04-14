package com.maple.rimaproject.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.model.searchSizeApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SharedPreferenceSize {

    public static final String PREFS_NAME = "PRODUCT_APP";
//    public static final String FAVORITES = "Product_Favorite";
    public static final String Size = "Projects";
    String key_name;


    public SharedPreferenceSize(String key_name) {
        super();
        this.key_name = key_name;
    }

    // This four methods are used for maintaining favorites.
    public void saveArrayList(Context context, List<searchSizeApi> favorites) {
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

    public void addArrayList(Context context, searchSizeApi product) {
        List<searchSizeApi> favorites = getArrayList(context);
        if (favorites == null)
            favorites = new ArrayList<searchSizeApi>();
        favorites.add(product);
        saveArrayList(context, favorites);
    }

    public void removeArrayList(Context context, searchSizeApi product) {
        ArrayList<searchSizeApi> favorites = getArrayList(context);
        if (favorites != null) {
            favorites.remove(product);
            saveArrayList(context, favorites);
        }
    }

    public ArrayList<searchSizeApi> getArrayList(Context context) {
        SharedPreferences settings;
        List<searchSizeApi> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(key_name)) {
            String jsonFavorites = settings.getString(key_name, null);
            Gson gson = new Gson();
            searchSizeApi[] favoriteItems = gson.fromJson(jsonFavorites,
                    searchSizeApi[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<searchSizeApi>(favorites);
        } else
            return null;

        return (ArrayList<searchSizeApi>) favorites;
    }
}