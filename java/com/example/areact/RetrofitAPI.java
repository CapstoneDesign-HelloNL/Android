package com.example.areact;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/api/auth/")
    Call<List<Post>> getData(@Query("userId") String id);

    @FormUrlEncoded
    @POST("/api/auth/signup")
    Call<Post> postData(@FieldMap HashMap<String, Object> param);

}
