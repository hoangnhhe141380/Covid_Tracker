package com.example.covid_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private final String API_URL = "https://api.covid19api.com/";
    private final String API_URL_SUMMARY = "https://corona.lmao.ninja/v2/";

    TextView tv_cases;
    Button btn_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_cases = findViewById(R.id.cases);
        btn_get = findViewById(R.id.button);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataByCountryName("vietnam");
            }
        });

    }

    public void getDataSummary() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_SUMMARY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataFromJson getDataFromJson = retrofit.create(GetDataFromJson.class);

        Call<List<Summary>> call = getDataFromJson.getDataSummary();

        call.enqueue(new Callback<List<Summary>>() {
            @Override
            public void onResponse(Call<List<Summary>> call, Response<List<Summary>> response) {
                List<Summary> summary = response.body();
                tv_cases.setText(summary.toString());
            }

            @Override
            public void onFailure(Call<List<Summary>> call, Throwable t) {
                tv_cases.setText(t.getMessage());
            }
        });
    }

    public void getDataByCountryName(String countryName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataFromJson getDataFromJson = retrofit.create(GetDataFromJson.class);

        Call<List<CountryData>> call = getDataFromJson.getDataByCountry(countryName);

        call.enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                List<CountryData> listCountryDayone = response.body();
                tv_cases.setText(listCountryDayone.toString());
            }

            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                tv_cases.setText(t.getMessage());
            }
        });
    }
}