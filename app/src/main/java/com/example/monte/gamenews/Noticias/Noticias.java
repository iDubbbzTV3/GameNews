package com.example.monte.gamenews.Noticias;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Noticias {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("game")
    @Expose
    private String game;

    @SerializedName("created_date")
    @Expose
    private String createdDate;

    @SerializedName("coverImage")
    @Expose
    private String coverImage;

    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("id" + id).append("title" + title).append("body" + body).
                append("game" + game).append("createdDate" + createdDate).append("coverImage" + coverImage).append("v" + v).toString();
    }

}
