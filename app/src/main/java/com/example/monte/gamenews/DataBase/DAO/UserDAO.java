package com.example.monte.gamenews.DataBase.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDAO {

    @Insert
    void insert(UserDAO user);

    @Query("SELECT * FROM user WHERE id=:id")
    UserDAO getUserById(String id);

    @Query("DELETE FROM user")
    void deleteAll();
}
