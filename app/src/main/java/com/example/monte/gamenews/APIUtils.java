package com.example.monte.gamenews;

public class APIUtils {

    public static final String baseURL = "http://gamenewsuca.herokuapp.com/";
    public static Notificaciones getAPIService(){
        return Client.getClient(baseURL).create(Notificaciones.class);
    }
}
