package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;
import com.example.covid_tracker.adapter.MedicalDeclaredAdapter;
import com.example.covid_tracker.adapter.SymptomsAdapter;
import com.example.covid_tracker.model.Account;
import com.example.covid_tracker.model.MedicalDeclaredData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentMedicalDeclaredData extends Fragment {
    private MainActivity mainActivity;
    private RecyclerView recyclerView;
    private final int NUMBER_OF_COLUMN = 1;
    private FirebaseDatabase database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Medical Declared Data");
        mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.layout_fragment_medical_declared_data, container, false);
        bindingView(view);
        DatabaseReference reference = bindingDatabaseRef();
        bindingAccountListener(reference);
 //       onMedicalDeclaredData(reference);
        return view;
    }
    private DatabaseReference bindingDatabaseRef() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/MedicalDeclared/+84944169551");
        return ref;
    }

    private void bindingAccountListener(DatabaseReference reference) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<MedicalDeclaredData> lst = new ArrayList<>();
                for (DataSnapshot data: snapshot.getChildren()
                     ) {
                    MedicalDeclaredData temp = data.getValue(MedicalDeclaredData.class);
                    lst.add(temp);
                }
                bindingData(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void bindingData(ArrayList<MedicalDeclaredData> lst){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, NUMBER_OF_COLUMN);
        recyclerView.setLayoutManager(gridLayoutManager);
        MedicalDeclaredAdapter adapter = new MedicalDeclaredAdapter(lst);
        recyclerView.setAdapter(adapter);
    }

    private void bindingView(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void onMedicalDeclaredData(DatabaseReference ref){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        ref.child("23-03-2022").setValue(new MedicalDeclaredData("3/23/2022", "3/23/2022", "3/23/2022", true, "ha noi", "loi loz"));
    }

}
