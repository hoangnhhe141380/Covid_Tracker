package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.covid_tracker.GetDataFromJson;
import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;
import com.example.covid_tracker.model.Summary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentCovidData extends Fragment {

    private final String SUMMARY_URL = "https://corona.lmao.ninja/v2/";

    private List<Summary> listSummary = new ArrayList<>();
    private List<String> listCountry = new ArrayList<>();

    private Spinner spinner;
    private MainActivity mainActivity;

//    private CountryPickerAdapter adapter;
    private ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Covid Data");

        View view = inflater.inflate(R.layout.layout_fragment_covid_data, container, false);

        mainActivity = (MainActivity) getActivity();
        spinner = view.findViewById(R.id.spinner);

        getListCountry();

//        adapter = new CountryPickerAdapter(mainActivity, listSummary);
        adapter = new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_item, listCountry);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mainActivity, adapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("A", 10000));
        data.add(new ValueDataEntry("B", 20000));
        data.add(new ValueDataEntry("C", 5000));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
        return view;
    }

    public void getListCountry() {
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
                for(Summary summary : listSummary){
                    listCountry.add(summary.getCountry());
                }

            }

            @Override
            public void onFailure(Call<List<Summary>> call, Throwable t) {
                listSummary = null;
            }
        });
    }
}
