package com.example.monte.gamenews.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {UserDAO.class, NewsDAO.class, PlayerDAO.class, FavNewsDAO.class}, version = 1)
public abstract class DataBase extends RoomDatabase{

    public abstract UserDAO userDAO();
    public abstract NewsDAO news();
    public abstract PlayerDAO player();
    public abstract FavNewsDAO favNews();
    private static Database INSTANCE;

    public static Database getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class,
                            "base_datos").build();
                }
            }
        }
        return INSTANCE;
    }

}
