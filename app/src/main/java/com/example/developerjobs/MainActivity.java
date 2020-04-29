package com.example.developerjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_jobs:
                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, MainFragment.newInstance())
                                .addToBackStack(null)
                                .commitAllowingStateLoss();
                        break;
                    case R.id.navigation_favourites:
                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, SavedJobsFragment.newInstance())
                                .addToBackStack(null)
                                .commitAllowingStateLoss();
                        break;
                    case R.id.navigation_more:
                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, MoreFragment.newInstance())
                                .addToBackStack(null)
                                .commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }
}
