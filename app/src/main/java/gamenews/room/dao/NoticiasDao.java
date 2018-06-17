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

    @Query("DELETE FROM News")
    void deleteAll();

    @Query("DELETE FROM News where game=:category")
    void deleteByCategory(String category);

    @Query("SELECT * from News WHERE _id=:id")
    News getNewByID(String id);

    @Query("SELECT * from News ORDER BY created_date DESC")
    LiveData<List<News>> getAllNews();

    @Query("SELECT * FROM News WHERE favorite=1 ORDER BY created_date DESC")
    LiveData<List<News>> getFavoritesNews();

    @Query("SELECT * from News WHERE game=:category ORDER BY created_date DESC ")
    LiveData<List<News>> getNewsByCategory(String category);

    @Query("SELECT coverImage from News WHERE game=:category ORDER BY created_date DESC ")
    LiveData<List<String>> getNewsImageByCategory(String category);

    @Query("SELECT game from News GROUP BY game ORDER BY game DESC ")
    LiveData<List<String>> getNewsCategory();

    @Query("UPDATE News SET favorite=1 WHERE _id=:id")
    void setFavorite(String id);

    @Query("UPDATE News SET favorite=0 WHERE _id=:id")
    void unsetFavorite(String id);







}
