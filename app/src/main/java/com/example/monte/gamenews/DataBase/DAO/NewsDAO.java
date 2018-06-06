package com.example.monte.gamenews.DataBase.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.monte.gamenews.DataBase.model.News;

@Dao
public interface NewsDAO {

    @Insert
    void insert(News news);

    @Query("DELETE FROM news")
    void deleteAll();

    @Query("SELECT * from news WHERE _id =:id")
    News getNewByID(String id);

    @Query("SELECT * from news")
    News getAllNews();
}
