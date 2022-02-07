package com.example.covid_tracker;

import java.util.List;

public class Summary {
    private String ID;
    private String Message;
    private Global Global;
    private List<Country> Countries;

    public Summary(String ID, String message, Global global, List<Country> countries) {
        this.ID = ID;
        Message = message;
        Global = global;
        Countries = countries;
    }

    public String getID() {
        return ID;
    }

    public String getMessage() {
        return Message;
    }

    public com.example.covid_tracker.Global getGlobal() {
        return Global;
    }

    public List<Country> getCountries() {
        return Countries;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "ID='" + ID + '\'' +
                ", Message='" + Message + '\'' +
                ", Global=" + Global +
                ", Countries=" + Countries +
                '}';
    }
}
