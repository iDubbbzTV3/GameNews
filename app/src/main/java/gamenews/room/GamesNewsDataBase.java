package gamenews.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import gamenews.room.dao.FavoriteDao;
import gamenews.room.dao.NoticiasDao;
import gamenews.room.dao.PlayerDao;
import gamenews.room.dao.UserDao;
import gamenews.room.model.Favorite;
import gamenews.room.model.News;
import gamenews.room.model.Player;
import gamenews.room.model.User;

@Database(entities = {News.class, Player.class, User.class, Favorite.class}, version = 1)
public abstract class GamesNewsDataBase extends RoomDatabase {

    public abstract NoticiasDao newDao();
    public  abstract PlayerDao playerDao();
    public  abstract UserDao userDao();
    public abstract FavoriteDao favoriteDao();
    private static GamesNewsDataBase INSTANCE;


    public static GamesNewsDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GamesNewsDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GamesNewsDataBase.class, "games_news_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
