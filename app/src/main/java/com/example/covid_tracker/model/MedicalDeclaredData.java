package com.example.covid_tracker.model;

public class MedicalDeclaredData {

    private Account account;
    private String dateStart;
    private String dateEnd;
    private boolean isContactWithF0;
    private String placeContact;
    private String symptoms;

    public MedicalDeclaredData(Account account, String dateStart, String dateEnd, boolean isContactWithF0, String placeContact, String symptoms) {
        this.account = account;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.isContactWithF0 = isContactWithF0;
        this.placeContact = placeContact;
        this.symptoms = symptoms;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isContactWithF0() {
        return isContactWithF0;
    }

    public void setContactWithF0(boolean contactWithF0) {
        isContactWithF0 = contactWithF0;
    }

    public String getPlaceContact() {
        return placeContact;
    }

    public void setPlaceContact(String placeContact) {
        this.placeContact = placeContact;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
