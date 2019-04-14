package com.maple.rimaproject.Retrofit;

import com.maple.rimaproject.model.featureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetAllSearch {

    @GET("app_api/search.php/")
    Call<List<Project>> getAllSearch(@Query("size") String size,@Query("price_from") String min_price,@Query("price_to") String max_price,@Query("features") String feature,@Query("type") String type );
}
