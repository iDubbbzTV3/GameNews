package com.example.monte.gamenews.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.ContactsContract;

import static com.example.monte.gamenews.DataBase.DataBase.DATABASE_VERSION;

@Database(entities = User.class, version = DATABASE_VERSION)
public abstract class DataBase extends RoomDatabase{
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="GAMENEWS-Database-Room";

    public abstract UserDAO userdao();

    private static Database mInstance;

    public static Database getInstance(Context context){
        if (mInstance == null){
            mInstance = Room.databaseBuilder(context, Database.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }

}
