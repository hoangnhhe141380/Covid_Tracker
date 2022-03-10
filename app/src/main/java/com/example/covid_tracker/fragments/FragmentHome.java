package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.covid_tracker.GetDataFromJson;
import com.example.covid_tracker.R;
import com.example.covid_tracker.model.Summary;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentHome extends Fragment {

    private final String SUMMARY_URL = "https://corona.lmao.ninja/v2";

    private ArrayList<Summary> list = new ArrayList<>();
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

        //getDataSummary();
        return view;
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
                List<Summary> listSummary = response.body();

                list.clear();
                for (Summary summary : listSummary) {
                    list.add(summary);
                }

                //Get total cases, deaths and recovered
                long totalCases = 0;
                long totalDeaths = 0;
                long totalRecovered = 0;

                for (Summary summary : list) {
                    totalCases += summary.getCases();
                    totalDeaths += summary.getDeaths();
                    totalRecovered += summary.getRecovered();
                }

                tv_cases.setText(totalCases == 0 ? "N/A" : totalCases + "");
                tv_deaths.setText(totalDeaths == 0 ? "N/A" : totalCases + "");
                tv_recovered.setText(totalRecovered == 0 ? "N/A" : totalCases + "");
            }

            @Override
            public void onFailure(Call<List<Summary>> call, Throwable t) {
                tv_date_updated.setText("N/A");
                tv_cases.setText("N/A");
                tv_deaths.setText("N/A");
                tv_recovered.setText("N/A");
            }
        });
    }
}
