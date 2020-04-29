package com.example.developerjobs;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SavedJobsFragment extends Fragment {

    AppDatabase appDatabase;
    SavedJobDao savedJobDao;
    List<SavedJob> savedJobs;
    List<Job> jobs = new ArrayList<>();
    RecyclerViewAdapter.ClickListener clickListener;
    RecyclerViewAdapter adapter;

    public static SavedJobsFragment newInstance() {
        SavedJobsFragment fragment = new SavedJobsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_jobs, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Saved jobs");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        clickListener = new RecyclerViewAdapter.ClickListener(){
            @Override
            public void replaceFragment(int id, Job job) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, JobDetailFragment.newInstance(job))
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        };

        adapter = new RecyclerViewAdapter(jobs, clickListener);
        recyclerView.setAdapter(adapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        for(SavedJob s : savedJobs){
            apiService.getJobById(s.id).enqueue(new Callback<Job>() {
                @Override
                public void onResponse(Call<Job> call, Response<Job> response) {
                    Job job = response.body();
                    jobs.add(job);
                    int index = jobs.size();
                    adapter.notifyItemInserted(index);
                }

                @Override
                public void onFailure(Call<Job> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = MyApplication.getInstance().getDatabase();
        savedJobDao = appDatabase.savedJobDao();
        savedJobs = savedJobDao.getSavedJobs();
    }
}
