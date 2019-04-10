package com.maple.rimaproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface GetUser {
    @GET("app_api/all_projects.php?action=all")
    Call<List<Datum>> createUser2();
}
