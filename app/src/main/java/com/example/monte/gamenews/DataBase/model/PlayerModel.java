package com.example.monte.gamenews.DataBase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "player")
public class PlayerModel {
    @ColumnInfo(name = "avatar")
    private String avatar;
    @PrimaryKey
    @NonNull@ColumnInfo (name="_id")
    private String id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="biografia")
    private String biografia;

    @ColumnInfo(name="game")
    private String game;

    public PlayerModel(String avatar, String id, String name, String biografia, String game) {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
        this.biografia = biografia;
        this.game = game;
    }

    public String getAvatar() {

        return avatar;
    }

    public void setAvatar(String avatar) {

        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
