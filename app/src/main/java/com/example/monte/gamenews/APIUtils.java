package com.example.monte.gamenews;

public class APIUtils {

    public static final String baseURL = "http://gamenewsuca.herokuapp.com/";
    public static Service getAPIService(){
        return Client.getClient(baseURL).create(Service.class);
    }
}
