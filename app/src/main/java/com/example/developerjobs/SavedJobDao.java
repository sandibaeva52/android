package com.example.developerjobs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedJobDao {

    @Query("SELECT * FROM saved_jobs")
    List<SavedJob> getSavedJobs();

    @Insert
    void insert(SavedJob savedJob);

    @Insert
    void delete(SavedJob savedJob);
}
