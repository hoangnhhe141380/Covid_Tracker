package com.example.covid_tracker;

public class Country {
    private String ID;
    private String Country;
    private String CountryCode;
    private String Slug;
    private long NewConfirmed;
    private long TotalConfirmed;
    private long NewDeaths;
    private long TotalDeaths;
    private long NewRecovered;
    private long TotalRecovered;
    private String Date;

    public Country(String ID, String country, String countryCode, String slug, long newConfirmed, long totalConfirmed, long newDeaths, long totalDeaths, long newRecovered, long totalRecovered, String date) {
        this.ID = ID;
        Country = country;
        CountryCode = countryCode;
        Slug = slug;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Date = date;
    }

    public String getID() {
        return ID;
    }

    public String getCountry() {
        return Country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getSlug() {
        return Slug;
    }

    public long getNewConfirmed() {
        return NewConfirmed;
    }

    public long getTotalConfirmed() {
        return TotalConfirmed;
    }

    public long getNewDeaths() {
        return NewDeaths;
    }

    public long getTotalDeaths() {
        return TotalDeaths;
    }

    public long getNewRecovered() {
        return NewRecovered;
    }

    public long getTotalRecovered() {
        return TotalRecovered;
    }

    public String getDate() {
        return Date;
    }
}
