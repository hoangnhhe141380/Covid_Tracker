package com.example.covid_tracker;

public class Global {
    private long NewConfirmed;
    private long TotalConfirmed;
    private long NewDeaths;
    private long TotalDeaths;
    private long NewRecovered;
    private long TotalRecovered;
    private String Date;

    public Global(long newConfirmed, long totalConfirmed, long newDeaths, long totalDeaths, long newRecovered, long totalRecovered, String date) {
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Date = date;
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
