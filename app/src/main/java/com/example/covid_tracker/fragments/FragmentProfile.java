package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;
import com.example.covid_tracker.model.Account;
import com.example.covid_tracker.model.Vaccine;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentProfile extends Fragment {
    private static final String TAG = "lmao";
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private EditText edt_name;
    private EditText edit_id;
    private EditText edit_bd;
    private EditText edit_gender;
    private EditText edit_phone;
    private EditText edt_vaccine_name1;
    private EditText edt_vaccine_unit1;
    private EditText edt_vaccine_type1;
    private EditText edt_vaccine_name2;
    private EditText edt_vaccine_unit2;
    private EditText edt_vaccine_type2;
    private EditText edt_vaccine_name3;
    private EditText edt_vaccine_unit3;
    private EditText edt_vaccine_type3;
    private Button btnEdit;
    private Button btnSave;
    private Button btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_profile, container, false);
        String  phoneNumber = "+84913556456";
        Bundle bundle = this.getArguments();
        if (null != bundle) {
            phoneNumber = bundle.getString("phoneNumber");
        }
        if (null == phoneNumber) {
            goToLogin();
            return view;
        }
        MainActivity.currentAccount = new Account();
        ref = bindingDatabaseRef(phoneNumber);
        bindingAccountListener(ref);
        bindingView(view);
        bindingAction();
        return view;
    }

    private void bindingAction() {
        btnEdit.setOnClickListener(this::onEditPrf);
        btnSave.setOnClickListener(this::onSavePrf);
        btnCancel.setOnClickListener(this::onCancelPrf);
    }

    private void onEditPrf(View view) {
        edt_name.setEnabled(true);
        edit_phone.setEnabled(true);
        edt_vaccine_name1.setEnabled(true);
        edt_vaccine_unit1.setEnabled(true);
        edt_vaccine_type1.setEnabled(true);
        edt_vaccine_name2.setEnabled(true);
        edt_vaccine_unit2.setEnabled(true);
        edt_vaccine_type2.setEnabled(true);
        edt_vaccine_name3.setEnabled(true);
        edt_vaccine_unit3.setEnabled(true);
        edt_vaccine_type3.setEnabled(true);
        btnEdit.setVisibility(View.GONE);
        btnCancel.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);
    }

    private void onSavePrf(View view) {
        updateData();
        ref.setValue(MainActivity.currentAccount);
        edt_name.setEnabled(false);
        edit_phone.setEnabled(false);
        edt_vaccine_name1.setEnabled(false);
        edt_vaccine_unit1.setEnabled(false);
        edt_vaccine_type1.setEnabled(false);
        edt_vaccine_name2.setEnabled(false);
        edt_vaccine_unit2.setEnabled(false);
        edt_vaccine_type2.setEnabled(false);
        edt_vaccine_name3.setEnabled(false);
        edt_vaccine_unit3.setEnabled(false);
        edt_vaccine_type3.setEnabled(false);
        btnEdit.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        bindingData();
    }

    private void onCancelPrf(View view) {
        edt_name.setEnabled(false);
        edit_phone.setEnabled(false);
        edt_vaccine_name1.setEnabled(false);
        edt_vaccine_unit1.setEnabled(false);
        edt_vaccine_type1.setEnabled(false);
        edt_vaccine_name2.setEnabled(false);
        edt_vaccine_unit2.setEnabled(false);
        edt_vaccine_type2.setEnabled(false);
        edt_vaccine_name3.setEnabled(false);
        edt_vaccine_unit3.setEnabled(false);
        edt_vaccine_type3.setEnabled(false);
        btnEdit.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
    }

    private void updateData() {
        MainActivity.currentAccount.setName(edt_name.getText().toString().trim());
        MainActivity.currentAccount.setPhone(edit_phone.getText().toString().trim());
        MainActivity.currentAccount.setVaccine1(new Vaccine(
                edt_vaccine_type1.getText().toString().trim(),
                edt_vaccine_unit1.getText().toString().trim(),
                edt_vaccine_name1.getText().toString().trim()));
        MainActivity.currentAccount.setVaccine2(new Vaccine(
                edt_vaccine_type2.getText().toString().trim(),
                edt_vaccine_unit2.getText().toString().trim(),
                edt_vaccine_name2.getText().toString().trim()));
        MainActivity.currentAccount.setVaccine3(new Vaccine(
                edt_vaccine_type3.getText().toString().trim(),
                edt_vaccine_unit3.getText().toString().trim(),
                edt_vaccine_name3.getText().toString().trim()));
    }

    private DatabaseReference bindingDatabaseRef(String phoneNumber) {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/Account/" + phoneNumber);
        return ref;
    }

    private void goToLogin() {
        MainActivity.setCurrentFragment(0);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentLogin());
        transaction.commit();
    }

    private void bindingView(View view) {
        edt_name = view.findViewById(R.id.edt_name);
        edit_id = view.findViewById(R.id.edt_id);
        edit_bd = view.findViewById(R.id.edt_bd);
        edit_gender = view.findViewById(R.id.edt_gender);
        edit_phone = view.findViewById(R.id.edt_phone);
        edt_vaccine_name1 = view.findViewById(R.id.edt_vaccine_name1);
        edt_vaccine_unit1 = view.findViewById(R.id.edt_vaccine_unit1);
        edt_vaccine_type1 = view.findViewById(R.id.edt_vaccine_type1);
        edt_vaccine_name2 = view.findViewById(R.id.edt_vaccine_name2);
        edt_vaccine_unit2 = view.findViewById(R.id.edt_vaccine_unit2);
        edt_vaccine_type2 = view.findViewById(R.id.edt_vaccine_type2);
        edt_vaccine_name3 = view.findViewById(R.id.edt_vaccine_name3);
        edt_vaccine_unit3 = view.findViewById(R.id.edt_vaccine_unit3);
        edt_vaccine_type3 = view.findViewById(R.id.edt_vaccine_type3);
        btnEdit = view.findViewById(R.id.btn_edit_prf);
        btnSave = view.findViewById(R.id.btn_save_prf);
        btnCancel = view.findViewById(R.id.btn_cancel_prf);
    }

    private void bindingData() {
        edt_name.setText(MainActivity.currentAccount.getName());
        edit_id.setText(MainActivity.currentAccount.getPersonalID());
        edit_bd.setText(MainActivity.currentAccount.getBirthDate());
        edit_gender.setText(MainActivity.currentAccount.isGender() ? "Male" : "Female");
        edit_phone.setText(MainActivity.currentAccount.getPhone());
        if (null != MainActivity.currentAccount.getVaccine1()) {
            edt_vaccine_name1.setText(MainActivity.currentAccount.getVaccine1().getVaccinationType());
            edt_vaccine_unit1.setText(MainActivity.currentAccount.getVaccine1().getVaccinationUnit());
            edt_vaccine_type1.setText(MainActivity.currentAccount.getVaccine1().getDate());
        } else {
            edt_vaccine_name1.setText("N/A");
            edt_vaccine_unit1.setText("N/A");
            edt_vaccine_type1.setText("N/A");
        }

        if (null != MainActivity.currentAccount.getVaccine2()) {
            edt_vaccine_name2.setText(MainActivity.currentAccount.getVaccine2().getVaccinationType());
            edt_vaccine_unit2.setText(MainActivity.currentAccount.getVaccine2().getVaccinationUnit());
            edt_vaccine_type2.setText(MainActivity.currentAccount.getVaccine2().getDate());
        } else {
            edt_vaccine_name2.setText("N/A");
            edt_vaccine_unit2.setText("N/A");
            edt_vaccine_type2.setText("N/A");
        }

        if (null != MainActivity.currentAccount.getVaccine3()) {
            edt_vaccine_name3.setText(MainActivity.currentAccount.getVaccine3().getVaccinationType());
            edt_vaccine_unit3.setText(MainActivity.currentAccount.getVaccine3().getVaccinationUnit());
            edt_vaccine_type3.setText(MainActivity.currentAccount.getVaccine3().getDate());
        } else {
            edt_vaccine_name3.setText("N/A");
            edt_vaccine_unit3.setText("N/A");
            edt_vaccine_type3.setText("N/A");
        }

    }

    private void bindingAccountListener(DatabaseReference reference) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()
                ) {
                    switch (data.getKey()) {
                        case "address": {
                            MainActivity.currentAccount.setAddress(data.getValue().toString());
                            break;
                        }
                        case "gender": {
                            MainActivity.currentAccount.setGender(data.getValue().toString().equals(1));
                            break;
                        }
                        case "name": {
                            MainActivity.currentAccount.setName(data.getValue().toString());
                            break;
                        }
                        case "phone": {
                            MainActivity.currentAccount.setPhone(data.getValue().toString());
                            break;
                        }
                        case "personalID": {
                            MainActivity.currentAccount.setPersonalID(data.getValue().toString());
                            break;
                        }
                        case "birthDate": {
                            MainActivity.currentAccount.setBirthDate(data.getValue().toString());
                            break;
                        }
                        case "vaccine1": {
                            MainActivity.currentAccount.setVaccine1(data.getValue(Vaccine.class));
                            break;
                        }
                        case "vaccine2": {
                            MainActivity.currentAccount.setVaccine2(data.getValue(Vaccine.class));
                            break;
                        }
                        case "vaccine3": {
                            MainActivity.currentAccount.setVaccine3(data.getValue(Vaccine.class));
                            break;
                        }
                    }
                }
                bindingData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
