package com.example.monte.gamenews.Token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public String getProcessedToken() {
        return "Bearer " + token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
