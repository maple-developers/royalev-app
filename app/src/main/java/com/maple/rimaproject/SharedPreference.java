//package com.maple.rimaproject;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//
//import com.google.gson.Gson;
//import com.maple.rimaproject.model.Project;
//
//public class SharedPreference {
//
//    public static final String PREFS_NAME = "PRODUCT_APP";
//    public static final String FAVORITES = "Product_Favorite";
//
//    public SharedPreference() {
//        super();
//    }
//
//    // This four methods are used for maintaining favorites.
//    public void saveArrayList(Context context, List<Project> favorites) {
//        SharedPreferences settings;
//        Editor editor;
//
//        settings = context.getSharedPreferences(PREFS_NAME,
//                Context.MODE_PRIVATE);
//        editor = settings.edit();
//
//        Gson gson = new Gson();
//        String jsonFavorites = gson.toJson(favorites);
//
//        editor.putString(FAVORITES, jsonFavorites);
//
//        editor.commit();
//    }
//
//    public void addArrayList(Context context, Project project) {
//        List<Project> favorites = getArrayList(context);
//        if (favorites == null)
//            favorites = new ArrayList<Project>();
//        favorites.add(Project);
//        saveArrayList(context, favorites);
//    }
//
//    public void removeArrayList(Context context, Project product) {
//        ArrayList<Project> favorites = getArrayList(context);
//        if (favorites != null) {
//            favorites.remove(product);
//            saveArrayList(context, favorites);
//        }
//    }
//
//    public ArrayList<Project> getArrayList(Context context) {
//        SharedPreferences settings;
//        List<Project> favorites;
//
//        settings = context.getSharedPreferences(PREFS_NAME,
//                Context.MODE_PRIVATE);
//
//        if (settings.contains(FAVORITES)) {
//            String jsonFavorites = settings.getString(FAVORITES, null);
//            Gson gson = new Gson();
//            Project[] favoriteItems = gson.fromJson(jsonFavorites,
//                    Project[].class);
//
//            favorites = Arrays.asList(favoriteItems);
//            favorites = new ArrayList<Project>(favorites);
//        } else
//            return null;
//
//        return (ArrayList<Project>) favorites;
//    }
//}