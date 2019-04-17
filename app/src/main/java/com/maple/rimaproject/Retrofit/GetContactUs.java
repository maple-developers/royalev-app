package com.maple.rimaproject.Retrofit;

import com.maple.rimaproject.model.featureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetContactUs {
    @GET("app_api/get_data.php?data_type=contact")
    Call<List<contactusmodel>> getinfo();
}
