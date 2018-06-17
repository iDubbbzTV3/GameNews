package gamenews.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import gamenews.room.model.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * from user WHERE _id=:id")
    User getUserByID(String id);

    @Query("SELECT * from user LIMIT 1")
    User getUser();


}
