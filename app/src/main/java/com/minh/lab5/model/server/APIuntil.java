package com.minh.lab5.model.server;

public class APIuntil {
    public static final String baseUrl = "http://asian.dotplays.com/";

    public static DataClient getData(){
        return RetrofitClient.getRetrofit(baseUrl).create(DataClient.class);
    }
}
