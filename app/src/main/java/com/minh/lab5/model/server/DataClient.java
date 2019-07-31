package com.minh.lab5.model.server;

import com.minh.lab5.model.data.Content;
import com.minh.lab5.model.data.Lab5;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataClient {
    @GET("wp-json/wp/v2/comments")
    Call<List<Lab5>> getLab(@Query("per_page") String per_page,
                               @Query("page") String page
                            );
}
