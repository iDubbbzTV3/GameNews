package gamenews.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import gamenews.room.model.Player;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Player> players);

    @Query("DELETE FROM player")
    void deleteAll();

    @Query("DELETE FROM player where game=:category")
    void deleteByCategory(String category);

    @Query("SELECT * from player WHERE _id=:id")
    Player getPlayerByID(String id);

    @Query("SELECT * from player WHERE game=:category")
    LiveData<List<Player>> getPlayersByCategory(String category);
}
