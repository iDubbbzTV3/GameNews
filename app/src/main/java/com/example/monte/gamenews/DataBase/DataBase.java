package com.example.monte.gamenews.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.monte.gamenews.DataBase.DAO.NewsDAO;
import com.example.monte.gamenews.DataBase.DAO.PlayerDAO;
import com.example.monte.gamenews.DataBase.DAO.UserDAO;

@Database(entities = {NewsDAO.class, UserDAO.class,PlayerDAO.class}, version = 1)
public abstract class DataBase extends RoomDatabase{

    public abstract UserDAO userDAO();
    public abstract NewsDAO newsDAO();
    public abstract PlayerDAO playerDAO();
    private static DataBase INSTANCE;

    public static DataBase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (DataBase.class){
                if (INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBase.class,
                            "base_datos").build();
                }
            }
        }
        return INSTANCE;
    }

}
