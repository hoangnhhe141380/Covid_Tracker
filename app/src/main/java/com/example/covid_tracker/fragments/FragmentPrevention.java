package com.example.covid_tracker.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;
import com.example.covid_tracker.adapter.PreventionAdapter;
import com.example.covid_tracker.adapter.SymptomsAdapter;
import com.example.covid_tracker.model.Prevention;
import com.example.covid_tracker.model.Symptom;

import java.util.ArrayList;
import java.util.List;

public class FragmentPrevention extends Fragment{

    private final int NUMBER_OF_COLUMN = 1;

    private RecyclerView recyclerView;
    private View view;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Prevention");
        view = inflater.inflate(R.layout.layout_fragment_prevention, container, false);

        mainActivity = (MainActivity) getActivity();

        recyclerView = view.findViewById(R.id.rcv_prevention);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, NUMBER_OF_COLUMN);
        recyclerView.setLayoutManager(gridLayoutManager);

        PreventionAdapter adapter = new PreventionAdapter(getListSymptoms());
        recyclerView.setAdapter(adapter);

        return view;
    }

    //Handle add data for recycle symptoms view
    private List<Prevention> getListSymptoms() {
        List<Prevention> listSymptoms = new ArrayList<>();
        listSymptoms.add(new Prevention(R.drawable.i_p1, "Wear A Face Mask", "Lorem ipsum dolor sit amet, conse ctetur adipisicing elit ipsum dolor."));
        listSymptoms.add(new Prevention(R.drawable.i_p2, "Wash Your Hands", "Lorem ipsum dolor sit amet, conse ctetur adipisicing elit ipsum dolor."));
        listSymptoms.add(new Prevention(R.drawable.i_p3, "Avoid Animals Contact", "Lorem ipsum dolor sit amet, conse ctetur adipisicing elit ipsum dolor."));
        listSymptoms.add(new Prevention(R.drawable.i_p4, "Well Done Cooking", "Lorem ipsum dolor sit amet, conse ctetur adipisicing elit ipsum dolor."));
        return listSymptoms;
    }

}
