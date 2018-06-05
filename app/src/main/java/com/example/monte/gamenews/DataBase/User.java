package com.example.monte.gamenews.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private String id;

    @ColumnInfo(name="user")
    private String user;

    @ColumnInfo(name="avatar")
    private String avatar;

    public User(String id, String user, String avatar){
        this.id = id;
        this.user = user;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    @Override
    public String toString(){
        return new StringBuilder().append("id:"+ id).append("\n").append("user:"+ user).append("\n")
                .append("avatar:"+ avatar).append("\n").toString();
    }
}
