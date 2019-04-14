package com.maple.rimaproject.Retrofit;

import com.maple.rimaproject.model.TypeModel;
import com.maple.rimaproject.model.searchSizeApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetType {

    @GET("app_api/get_data.php?data_type=sizes")
    Call<List<TypeModel>> gettype();
}
