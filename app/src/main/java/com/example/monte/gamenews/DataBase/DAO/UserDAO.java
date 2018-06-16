package com.example.monte.gamenews.DataBase.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.monte.gamenews.DataBase.model.UserModel;

@Dao
public interface UserDAO {

    @Insert
    void insert(UserModel user);

    @Query("SELECT * FROM user WHERE id=:id")
    UserModel getUserById(String id);

    @Query("DELETE FROM user")
    void deleteAll();
}
