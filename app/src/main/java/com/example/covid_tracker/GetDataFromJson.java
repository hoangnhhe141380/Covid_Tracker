package com.example.covid_tracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataFromJson {
    @GET("countries")
    Call<List<CountryData>> getJson();

}