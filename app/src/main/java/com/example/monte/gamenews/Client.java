package com.example.monte.gamenews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Client {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseURL){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
