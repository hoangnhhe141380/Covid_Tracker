package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_tracker.R;

public class FragmentLogin extends Fragment {

    private TextView tvSkip;
    private Button btnLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("SIGN UP - STEP 1/2");
        View view = inflater.inflate(R.layout.layout_fragment_login, container, false);

        tvSkip = (TextView) view.findViewById(R.id.tv_skip);

        //Should add on click listener event for tvSkip to navigate to home fragment

        //Should add on click listener event for btnLogin to navigate to password fragment

        return view;
    }

}
