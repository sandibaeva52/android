package com.example.developerjobs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SavedJob.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SavedJobDao savedJobDao();
}
