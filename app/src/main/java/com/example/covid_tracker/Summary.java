package com.example.covid_tracker;

import java.util.List;

public class Summary {
    private long updated;
    private String country;
    private CountryInfo countryInfo;
    private long cases;
    private long todayCases;
    private long deaths;
    private long todayDeaths;
    private long recovered;
    private long todayRecovered;
    private long active;
    private long critical;
    private long casesPerOneMillion;
    private long deathsPerOneMillion;
    private long tests;
    private long testsPerOneMillion;
    private long population;
    private String continent;
    private long oneCasePerPeople;
    private long oneDeathPerPeople;
    private long oneTestPerPeople;
    private long activePerOneMillion;
    private long recoveredPerOneMillion;
    private long criticalPerOneMillion;

    public long getUpdated() {
        return updated;
    }

    public String getCountry() {
        return country;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public long getCases() {
        return cases;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public long getDeaths() {
        return deaths;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getTodayRecovered() {
        return todayRecovered;
    }

    public long getActive() {
        return active;
    }

    public long getCritical() {
        return critical;
    }

    public long getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public long getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public long getTests() {
        return tests;
    }

    public long getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public long getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public long getOneCasePerPeople() {
        return oneCasePerPeople;
    }

    public long getOneDeathPerPeople() {
        return oneDeathPerPeople;
    }

    public long getOneTestPerPeople() {
        return oneTestPerPeople;
    }

    public long getActivePerOneMillion() {
        return activePerOneMillion;
    }

    public long getRecoveredPerOneMillion() {
        return recoveredPerOneMillion;
    }

    public long getCriticalPerOneMillion() {
        return criticalPerOneMillion;
    }
}