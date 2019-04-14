package com.maple.rimaproject.Retrofit;


import com.maple.rimaproject.model.searchSizeApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetSizeSearch {
    @GET("app_api/get_data.php?data_type=types")
    Call<List<searchSizeApi>> getSearch();
}
