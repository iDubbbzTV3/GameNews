package com.example.monte.gamenews.Usuarios;

import com.example.monte.gamenews.DataBase.model.NewsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GPubFavUsuario {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("add")
    @Expose
    public NewsModel add;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public NewsModel getAdd() {
        return add;
    }

    public void setAdd(NewsModel add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("success" + success).append("add" + add).toString();
    }

}
