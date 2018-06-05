package com.example.monte.gamenews.Usuarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.monte.gamenews.Noticias.Noticias;

public class GPubFavUsuario {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("add")
    @Expose
    public Noticias add;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Noticias getAdd() {
        return add;
    }

    public void setAdd(Noticias add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("success" + success).append("add" + add).toString();
    }

}
