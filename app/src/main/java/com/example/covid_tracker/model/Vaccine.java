package com.example.covid_tracker.model;

public class Vaccine {
    private String date;
    private String vaccinationUnit;
    private String vaccinationType;

    public Vaccine() {
    }

    public Vaccine(String date, String vaccinationUnit, String vaccinationType) {
        this.date = date;
        this.vaccinationUnit = vaccinationUnit;
        this.vaccinationType = vaccinationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVaccinationUnit() {
        return vaccinationUnit;
    }

    public void setVaccinationUnit(String vaccinationUnit) {
        this.vaccinationUnit = vaccinationUnit;
    }

    public String getVaccinationType() {
        return vaccinationType;
    }

    public void setVaccinationType(String vaccinationType) {
        this.vaccinationType = vaccinationType;
    }
}
