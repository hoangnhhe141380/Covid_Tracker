package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;

public class FragmentLogout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_logout, container, false);
        MainActivity.currentAccount = null;
        setMenu();
        goToLogin();
        return view;
    }
    private void goToLogin() {
        MainActivity.setCurrentFragment(0);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentLogin());
        transaction.commit();
    }

    private void setMenu(){
        MenuItem navigation_login   = MainActivity.menu.findItem(R.id.navigation_login);
        MenuItem navigation_logout  = MainActivity.menu.findItem(R.id.navigation_logout);
        navigation_login.setVisible(true);
        navigation_logout.setVisible(false);
    }
}
