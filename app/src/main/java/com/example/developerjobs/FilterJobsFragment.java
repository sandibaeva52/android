package com.example.developerjobs;

import android.app.DialogFragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;


public class FilterJobsFragment extends DialogFragment {

    EditText description, location;
    CheckBox full_time;
    Button filter;

    public FilterJobsFragment() {

    }

    public static FilterJobsFragment newInstance() {
        FilterJobsFragment fragment = new FilterJobsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_jobs, container, false);
        description = view.findViewById(R.id.description);
        location = view.findViewById(R.id.location);
        full_time = view.findViewById(R.id.full_time);
        filter = view.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = description.getText().toString();
                String l = location.getText().toString();
                boolean is_full_time = full_time.isChecked();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance(d, l, is_full_time))
                        .commitAllowingStateLoss();
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
