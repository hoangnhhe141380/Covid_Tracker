package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_tracker.GetDataFromJson;
import com.example.covid_tracker.R;
import com.example.covid_tracker.model.Summary;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentHome extends Fragment {

    private final String SUMMARY_URL = "https://corona.lmao.ninja/v2/";

    private List<Summary> listSummary = new ArrayList<>();

    private TextView tv_date_updated;
    private TextView tv_cases;
    private TextView tv_deaths;
    private TextView tv_recovered;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Covid Tracker");
        View view = inflater.inflate(R.layout.layout_fragment_home, container, false);

        tv_date_updated = view.findViewById(R.id.tv_date_updated);
        tv_cases = view.findViewById(R.id.tv_cases);
        tv_deaths = view.findViewById(R.id.tv_deaths);
        tv_recovered = view.findViewById(R.id.tv_recovered);

        Calendar currentDate = Calendar.getInstance();
        String date = DateFormat.getInstance().format(currentDate.getTime());

        tv_date_updated.setText(date);

        getDataSummary();

        Button button_covid_data_more = view.findViewById(R.id.button_covid_data_more);
        Button button_medical_declared_data = view.findViewById(R.id.button_medical_declared_data);
        Button button_about_virus = view.findViewById(R.id.button_about_virus);
        Button button_symptoms = view.findViewById(R.id.button_symptoms);
        Button button_prevention = view.findViewById(R.id.button_prevention);

        button_covid_data_more.setOnClickListener(this::onBtnCovidDataMore);
        button_medical_declared_data.setOnClickListener(this::onBtnMedicalDeclaredData);
        button_about_virus.setOnClickListener(this::onBtnAboutVirus);
        button_symptoms.setOnClickListener(this::onBtnSymptoms);
        button_prevention.setOnClickListener(this::onBtnPrevention);

        return view;
    }


    private void onBtnCovidDataMore(View view) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentCovidData());
        transaction.commit();
    }

    private void onBtnMedicalDeclaredData(View view) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentMedicalDeclaredData());
        transaction.commit();
    }

    private void onBtnAboutVirus(View view) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentAboutVirus());
        transaction.commit();
    }

    private void onBtnSymptoms(View view) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentSymptoms());
        transaction.commit();
    }

    private void onBtnPrevention(View view) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new FragmentPrevention());
        transaction.commit();
    }

    public void getDataSummary() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SUMMARY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataFromJson getDataFromJson = retrofit.create(GetDataFromJson.class);

        Call<List<Summary>> call = getDataFromJson.getDataSummary();

        call.enqueue(new Callback<List<Summary>>() {
            @Override
            public void onResponse(Call<List<Summary>> call, Response<List<Summary>> response) {
                listSummary = response.body();

                if (listSummary.isEmpty()) {
                    tv_date_updated.setText("N/A");
                    tv_cases.setText("N/A");
                    tv_deaths.setText("N/A");
                    tv_recovered.setText("N/A");
                } else {
                    //Get total cases, deaths and recovered
                    long totalCases = 0;
                    long totalDeaths = 0;
                    long totalRecovered = 0;

                    for (Summary summary : listSummary) {
                        totalCases += summary.getCases();
                        totalDeaths += summary.getDeaths();
                        totalRecovered += summary.getRecovered();
                    }

                    String stringTotalCases = totalCases == 0 ? "N/A" : NumberFormat.getNumberInstance(Locale.US).format(totalCases);
                    String stringTotalDeaths = totalDeaths == 0 ? "N/A" : NumberFormat.getNumberInstance(Locale.US).format(totalDeaths);
                    String stringTotalRecovered = totalRecovered == 0 ? "N/A" : NumberFormat.getNumberInstance(Locale.US).format(totalRecovered);

                    tv_cases.setText(stringTotalCases);
                    tv_deaths.setText(stringTotalDeaths);
                    tv_recovered.setText(stringTotalRecovered);
                }

            }

            @Override
            public void onFailure(Call<List<Summary>> call, Throwable t) {
                tv_date_updated.setText(t.getMessage());
                tv_cases.setText("Error");
                tv_deaths.setText("Error");
                tv_recovered.setText("Error");
            }
        });
    }
}
