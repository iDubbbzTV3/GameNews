package com.example.monte.gamenews.DataBase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity
public class Favoritos {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    @ColumnInfo(name = "_idnew")
    private String idNew;


    public Favoritos(@NonNull int id, String idNew) {
        this.id = id;
        this.idNew = idNew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNew() {
        return idNew;
    }

    public void setIdNew(String idNew) {
        this.idNew = idNew;
    }
}
