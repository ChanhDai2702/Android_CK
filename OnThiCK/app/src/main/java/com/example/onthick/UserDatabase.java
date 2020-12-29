package com.example.onthick;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase database;
    private static  String DB_Name = "onthick";
    public synchronized static UserDatabase getInstance(Context  context){

        if(database==null){
            database= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DB_Name)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
public abstract UserDao userDao();
}
