package com.example.developerjobs;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {
    public static MyApplication instance;
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "saved_jobs_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public AppDatabase getDatabase(){
        return appDatabase;
    }
}
