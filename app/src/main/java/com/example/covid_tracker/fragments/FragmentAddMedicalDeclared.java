package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;
import com.example.covid_tracker.model.MedicalDeclaredData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentAddMedicalDeclared extends Fragment {
    private EditText edt_add_date_from;
    private EditText edt_add_date_to;
    private EditText edt_add_place_contact;
    private EditText edt_add_symptom;
    private CheckBox edt_add_contact_with_f0;
    private Button btn_add_medical_declared;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Add Medical Declared");
        View view = inflater.inflate(R.layout.layout_medical_declared_add, container, false);
        bindingView(view);
        bindingAction();
        return view;
    }

    private void bindingView(View view) {
        edt_add_date_from = view.findViewById(R.id.edt_add_date_from);
        edt_add_date_to = view.findViewById(R.id.edt_add_date_to);
        edt_add_place_contact = view.findViewById(R.id.edt_add_place_contact);
        edt_add_symptom = view.findViewById(R.id.edt_add_symptom);
        edt_add_contact_with_f0 = view.findViewById(R.id.edt_add_contact_with_f0);
        btn_add_medical_declared = view.findViewById(R.id.btn_add_medical_declared);
    }

    private void bindingAction() {
        btn_add_medical_declared.setOnClickListener(this::onAddMedicalDeclared);
    }

    private void onAddMedicalDeclared(View view) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dateFrom = edt_add_date_from.getText().toString().trim();
        String dateTo = edt_add_date_to.getText().toString().trim();
        String placeContact = edt_add_place_contact.getText().toString().trim();
        String symptom = edt_add_symptom.getText().toString().trim();
        boolean isContact = edt_add_contact_with_f0.isChecked();
        MedicalDeclaredData data = new MedicalDeclaredData(
                format.format(date),
                dateFrom,
                dateTo,
                isContact,
                placeContact,
                symptom);
        submitMedicalDeclared(data);
    }

    private void submitMedicalDeclared(MedicalDeclaredData medicalDeclaredData) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/MedicalDeclared/" + MainActivity.currentAccount.getPhone());
        ref.child(medicalDeclaredData.getDateDeclare()).setValue(medicalDeclaredData);
        goToMedicalDeclaredData();
    }

    private void goToMedicalDeclaredData(){
        MainActivity.setCurrentFragment(4);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentMedicalDeclaredData());
        transaction.commit();
    }
}
