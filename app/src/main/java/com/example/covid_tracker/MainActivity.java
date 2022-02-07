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

    private String COUNTRY_DATA_URL = "https://corona.lmao.ninja/v2/";

    TextView tv_cases, tv_deaths, tv_recoveries;
    Button btn_get;
    List<CountryData> listCountryData;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_cases = findViewById(R.id.cases);
        tv_deaths = findViewById(R.id.deaths);
        tv_recoveries = findViewById(R.id.recoveries);
        btn_get = findViewById(R.id.button);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(COUNTRY_DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataFromJson getDataFromJson = retrofit.create(GetDataFromJson.class);

        Call<List<CountryData>> call = getDataFromJson.getJson();

        call.enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                listCountryData = response.body();
                for (CountryData c : listCountryData) {
                    tv_cases.append("Case: " + c.getCountryInfo().getIso3() + "\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                tv_cases.setText(t.getMessage());
            }
        });


    }
}