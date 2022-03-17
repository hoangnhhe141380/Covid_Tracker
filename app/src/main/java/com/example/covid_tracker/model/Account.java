package com.example.covid_tracker.model;

import java.util.List;

public class Account {
    private String address;
    private String birthDate;
    private boolean gender;
    private String name;
    private Integer personalID;
    private String phone;
    private List<Vaccine> vaccineList;

    public Account(String address, String birthDate, boolean gender, String name, Integer personalID, String phone, List<Vaccine> vaccineList) {
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.name = name;
        this.personalID = personalID;
        this.phone = phone;
        this.vaccineList = vaccineList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Integer personalID) {
        this.personalID = personalID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(List<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }
}
