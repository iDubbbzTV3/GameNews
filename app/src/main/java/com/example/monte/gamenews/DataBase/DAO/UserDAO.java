package com.example.monte.gamenews.DataBase.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.monte.gamenews.DataBase.model.User;

@Dao
public interface UserDAO {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE id=:id")
    User getUserById(String id);

    @Query("DELETE FROM user")
    void deleteAll();
}
