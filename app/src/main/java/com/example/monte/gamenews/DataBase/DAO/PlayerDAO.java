package com.example.monte.gamenews.DataBase.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.monte.gamenews.DataBase.model.Player;

@Dao
public interface PlayerDAO {
    @Insert
    void insert(Player player);

    @Query("DELETE  FROM player")
    void deleteAll();

    @Query("SELECT * from player WHERE _id=:id")
    Player getPlayerByID(String id);
}
