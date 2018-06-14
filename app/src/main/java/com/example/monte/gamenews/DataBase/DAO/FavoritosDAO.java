package com.example.monte.gamenews.DataBase.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.monte.gamenews.DataBase.model.Favoritos;
import com.example.monte.gamenews.DataBase.model.News;

import java.util.List;

@Dao
public interface FavoritosDAO {

    @Insert
    void insert(Favoritos favoritos);

    @Query("DELETE FROM favoritos")
    void deleteAll();

    @Query("DELETE FROM favoritos Where _id = :id")
    void deletefav(String id);

    //@Query("SELECT _idnew from favoritos")
    //    LiveData<List<String>> getFavorites(String id);

    @Query("SELECT * from news")
    News getAllNews();
}
