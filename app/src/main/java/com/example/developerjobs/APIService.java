package com.example.developerjobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("positions.json?page=1&markdown=true")
    Call<List<Job>> getJobs();

    @GET("positions.json?markdown=true")
    Call<List<Job>> getJobsFromSearch(@Query("search") String search);

    @GET("positions.json?markdown=true")
    Call<List<Job>> getFilteredJobs(@Query("description") String description,
                                    @Query("location") String location,
                                    @Query("full_time") boolean full_time);

    @GET("positions/{id}.json?markdown=true")
    Call<Job> getJobById(@Path("id") String jobId);
}
