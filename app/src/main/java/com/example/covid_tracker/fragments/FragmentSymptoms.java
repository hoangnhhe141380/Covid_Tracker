package com.example.covid_tracker.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;
import com.example.covid_tracker.adapter.SymptomsAdapter;
import com.example.covid_tracker.model.Symptom;

import java.util.ArrayList;
import java.util.List;

public class FragmentSymptoms extends Fragment {

    private final int NUMBER_OF_COLUMN = 2;

    private RecyclerView recyclerView;
    private View view;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Symptoms");
        view = inflater.inflate(R.layout.layout_fragment_symptoms, container, false);
        mainActivity = (MainActivity) getActivity();

        recyclerView = view.findViewById(R.id.rcv_symptoms);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, NUMBER_OF_COLUMN);
        recyclerView.setLayoutManager(gridLayoutManager);

        SymptomsAdapter adapter = new SymptomsAdapter(getListSymptoms());
        recyclerView.setAdapter(adapter);

        return view;
    }

    //Handle add data for recycle symptoms view
    private List<Symptom> getListSymptoms() {
        List<Symptom> listSymptoms = new ArrayList<>();
        listSymptoms.add(new Symptom(R.drawable.i_s1, "Coughing & Sneezing", "Lorem abasdgasdgasdasgasdggsdagasdgasdgsdf"));
        listSymptoms.add(new Symptom(R.drawable.i_s2, "Strong Headache", "Lorem abasdf"));
        listSymptoms.add(new Symptom(R.drawable.i_s3, "Shortness Of Breath", "Lorem abasdf"));
        listSymptoms.add(new Symptom(R.drawable.i_s4, "High Fever", "Lorem abasdf"));
        listSymptoms.add(new Symptom(R.drawable.i_s5, "Sore Throat", "Lorem abasasdgasdgasgdgasdsgdsgdasgdsgagsddf"));
        listSymptoms.add(new Symptom(R.drawable.i_s6, "Kidney Failure", "Lorem abasdf"));
        return listSymptoms;
    }
}
