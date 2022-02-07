package com.example.covid_tracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataFromJson {
    //Fetch data from https://api.covid19api.com/countries
    @GET("/summary")
    Call<Summary> getDataSummary();

    //Fetch data countries from https://api.covid19api.com/countries
    @GET("/countries")
    Call<List<Summary>> getDataAllCountries();

    //Fetch all data by country from https://api.covid19api.com/dayone/country/{countryName}
    //Example https://api.covid19api.com/dayone/country/vietnam
    @GET("/dayone/country/{countryName}")
    Call<List<Summary>> getDataByCountry(@Path("countryName") String countryName);

}