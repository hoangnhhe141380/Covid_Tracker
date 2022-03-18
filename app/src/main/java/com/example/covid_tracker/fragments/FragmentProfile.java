package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private static final String TAG ="lmao";
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private EditText edt_name;
    private EditText edit_id;
    private EditText edit_bd;
    private EditText edit_gender;
    private EditText edit_phone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_profile, container, false);
        String phoneNumber = null;
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
        return view;
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
    }

    private void bindingData() {
        edt_name.setText(MainActivity.currentAccount.getName());
        edit_id.setText(MainActivity.currentAccount.getPersonalID());
        edit_bd.setText(MainActivity.currentAccount.getBirthDate());
        edit_gender.setText(MainActivity.currentAccount.isGender() ? "Male" : "Female");
        edit_phone.setText(MainActivity.currentAccount.getPhone());
    }

    private void bindingAccountListener(DatabaseReference reference) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()
                ) {
                    switch (data.getKey()) {
                        case "Address": {
                            MainActivity.currentAccount.setAddress(data.getValue().toString());
                            break;
                        }
                        case "Gender": {
                            MainActivity.currentAccount.setGender(data.getValue().toString().equals(1));
                            break;
                        }
                        case "Name": {
                            MainActivity.currentAccount.setName(data.getValue().toString());
                            break;
                        }
                        case "Phone": {
                            MainActivity.currentAccount.setPhone(data.getValue().toString());
                            break;
                        }
                        case "PersonalID": {
                            MainActivity.currentAccount.setPersonalID(data.getValue().toString());
                            break;
                        }
                        case "BirthDate": {
                            MainActivity.currentAccount.setBirthDate(data.getValue().toString());
                            break;
                        }
//                        case "Vaccine1": {
//                            account.setVaccine1(data.getValue(Vaccine.class));
//                            Log.e(TAG, account.getVaccine1().getDate());
//                            break;
//                        }
//                        case "Vaccine2": {
//                            account.setVaccine2(data.getValue(Vaccine.class));
//                            Log.e(TAG, account.getVaccine2().getDate());
//                            break;
//                        }
//                        case "Vaccine3": {
//                            account.setVaccine3(data.getValue(Vaccine.class));
//                            Log.e(TAG, account.getVaccine3().getDate());
//                            break;
//                        }
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
