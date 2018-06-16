package com.example.monte.gamenews.DataBase.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.monte.gamenews.DataBase.model.FavoritosModel;
import com.example.monte.gamenews.DataBase.model.NewsModel;
import java.util.List;

@Dao
public interface FavoritosDAO {

    @Insert
    void insert(FavoritosModel fav);

    @Query("DELETE FROM favoritos")
    void deleteAll();

    @Query("SELECT * from favoritos WHERE _id=:id")
    FavoritosModel getFavoriteByID(String id);

    @Query("SELECT * FROM news INNER JOIN favoritos ON news._id=favoritos._id WHERE favoritos.idusuario=:id")
    List<NewsModel> getFavoriteNews(int id);


}
