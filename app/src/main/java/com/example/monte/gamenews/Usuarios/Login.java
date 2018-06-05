package com.example.monte.gamenews.Usuarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Login {

        @SerializedName("token")
        @Expose
        private String token;

        public String getToken() {
            return "Bearer " + token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("token"+ token).toString();
        }

    }

