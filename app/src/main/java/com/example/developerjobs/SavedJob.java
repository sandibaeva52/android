package com.example.developerjobs;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_jobs")
public class SavedJob {
    @PrimaryKey
    @NonNull
    public String id;

    public SavedJob() {
    }

    public SavedJob(String id) {
        this.id = id;
    }
}
