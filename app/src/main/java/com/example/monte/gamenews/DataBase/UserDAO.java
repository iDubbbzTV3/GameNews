package com.example.monte.gamenews.DataBase;

import io.reactivex.Flowable;
import android.arch.persistence.room.Query;
import java.util.List;

public interface UserDAO {

    @Query("SELECT * FROM users WHERE id=:userId")
    Flowable<User> getUserById(int userId);

    @Query("SELECT * FROM users")
    Flowable<List<User>> getAllUsers();

}
