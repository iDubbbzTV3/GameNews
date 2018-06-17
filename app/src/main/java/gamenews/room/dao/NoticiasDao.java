package gamenews.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import gamenews.room.model.News;
import gamenews.room.model.Player;


@Dao
public interface NoticiasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News n_new);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Player> player);

    @Query("DELETE FROM New")
    void deleteAll();

    @Query("DELETE FROM New where game=:category")
    void deleteByCategory(String category);

    @Query("SELECT * from New WHERE _id=:id")
    News getNewByID(String id);

    @Query("SELECT * from New ORDER BY created_date DESC")
    LiveData<List<News>> getAllNews();

    @Query("SELECT * FROM New WHERE favorite=1 ORDER BY created_date DESC")
    LiveData<List<News>> getFavoritesNews();

    @Query("SELECT * from New WHERE game=:category ORDER BY created_date DESC ")
    LiveData<List<News>> getNewsByCategory(String category);

    @Query("SELECT coverImage from New WHERE game=:category ORDER BY created_date DESC ")
    LiveData<List<String>> getNewsImageByCategory(String category);

    @Query("SELECT game from New GROUP BY game ORDER BY game DESC ")
    LiveData<List<String>> getNewsCategory();

    @Query("UPDATE New SET favorite=1 WHERE _id=:id")
    void setFavorite(String id);

    @Query("UPDATE New SET favorite=0 WHERE _id=:id")
    void unsetFavorite(String id);







}
