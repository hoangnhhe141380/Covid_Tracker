package com.example.covid_tracker.model;

public class CountryData {

    private String ID;
    private String Country;
    private String CountryCode;
    private String Province;
    private String City;
    private String CityCode;
    private long Confirmed;
    private long Deaths;
    private long Recovered;
    private long Active;
    private String Date;

    public String getID() {
        return ID;
    }

    public String getCountry() {
        return Country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getProvince() {
        return Province;
    }

    public String getCity() {
        return City;
    }

    public String getCityCode() {
        return CityCode;
    }

    public long getConfirmed() {
        return Confirmed;
    }

    public long getDeaths() {
        return Deaths;
    }

    public long getRecovered() {
        return Recovered;
    }

    public long getActive() {
        return Active;
    }

    public String getDate() {
        return Date;
    }
}
