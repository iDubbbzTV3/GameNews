package com.example.monte.gamenews.DataBase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "news")
public class News {
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "_id")
    private String id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "game")
    private String game;

    @ColumnInfo(name = "coverImage")
    private String coverImage;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "favoritos")
    private String favoritos;

    public News() {

    }

    public News(@NonNull String id, String title, String body, String game, String coverImage, String description, String favoritos){
        this.id = id;
        this.title = title;
        this.body = body;
        this.game = game;
        this.coverImage = coverImage;
        this.description = description;
        this.favoritos = favoritos;
    }

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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(String favoritos) {
        this.favoritos = favoritos;
    }
}
