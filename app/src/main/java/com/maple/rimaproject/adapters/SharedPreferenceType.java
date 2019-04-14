package com.maple.rimaproject.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.maple.rimaproject.model.TypeModel;
import com.maple.rimaproject.model.searchSizeApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SharedPreferenceType {

    public static final String PREFS_NAME = "PRODUCT_APP";
//    public static final String FAVORITES = "Product_Favorite";
    public static final String Type = "Type";
    String key_name;


    public SharedPreferenceType(String key_name) {
        super();
        this.key_name = key_name;
    }

    // This four methods are used for maintaining favorites.
    public void saveArrayList(Context context, List<TypeModel> favorites) {
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

    public void addArrayList(Context context, TypeModel product) {
        List<TypeModel> favorites = getArrayList(context);
        if (favorites == null)
            favorites = new ArrayList<TypeModel>();
        favorites.add(product);
        saveArrayList(context, favorites);
    }

    public void removeArrayList(Context context, TypeModel product) {
        ArrayList<TypeModel> favorites = getArrayList(context);
        if (favorites != null) {
            favorites.remove(product);
            saveArrayList(context, favorites);
        }
    }

    public ArrayList<TypeModel> getArrayList(Context context) {
        SharedPreferences settings;
        List<TypeModel> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(key_name)) {
            String jsonFavorites = settings.getString(key_name, null);
            Gson gson = new Gson();
            TypeModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    TypeModel[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<TypeModel>(favorites);
        } else
            return null;

        return (ArrayList<TypeModel>) favorites;
    }
}