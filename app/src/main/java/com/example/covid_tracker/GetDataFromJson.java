package com.example.covid_tracker;

import com.example.covid_tracker.model.CountryData;
import com.example.covid_tracker.model.Summary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataFromJson {
    //Fetch summary data from https://corona.lmao.ninja/v2/countries
    @GET("countries")
    Call<List<Summary>> getDataSummary();

    //Fetch all data by country from https://api.covid19api.com/dayone/country/{countryName}
    //Example https://api.covid19api.com/dayone/country/vietnam
    @GET("dayone/country/{countryName}")
    Call<List<CountryData>> getDataByCountry(@Path("countryName") String countryName);

    //Tách retrofit sang file này, rồi return về data fetch đc
}