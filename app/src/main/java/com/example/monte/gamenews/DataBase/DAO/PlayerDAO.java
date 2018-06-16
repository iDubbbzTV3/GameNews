package com.example.monte.gamenews.DataBase.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.monte.gamenews.DataBase.model.PlayerModel;

@Dao
public interface PlayerDAO {
    @Insert
    void insert(PlayerModel player);

    @Query("DELETE  FROM player")
    void deleteAll();

    @Query("SELECT * from player WHERE _id=:id")
    PlayerModel getPlayerByID(String id);
}
