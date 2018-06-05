package com.example.monte.gamenews.DataBase;

import io.reactivex.Flowable;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

public interface UserDAO {

    @Insert
    void insert(UserDAO user);

    @Query("SELECT * FROM users WHERE id=:userId")
    UserDAO getUserById(String id);

    @Query("DELETE FROM user")
    void deleteAll();
}
