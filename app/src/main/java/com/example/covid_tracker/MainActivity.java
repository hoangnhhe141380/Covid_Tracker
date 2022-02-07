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

    private String COUNTRY_DATA_URL = "https://api.covid19api.com/";

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

        Call<Summary> call = getDataFromJson.getDataSummary();

        call.enqueue(new Callback<Summary>() {
            @Override
            public void onResponse(Call<Summary> call, Response<Summary> response) {
                Summary summary = response.body();
                tv_cases.setText(summary.toString());
            }

            @Override
            public void onFailure(Call<Summary> call, Throwable t) {
                tv_cases.setText(t.getMessage());
            }
        });


    }
}