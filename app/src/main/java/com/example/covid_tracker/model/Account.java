package com.example.covid_tracker.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@IgnoreExtraProperties
public class Account implements Serializable{
    private String address;
    private String birthDate;
    private boolean gender;
    private String name;
    private String personalID;
    private String phone;
    private Vaccine vaccine1;
    private Vaccine vaccine2;
    private Vaccine vaccine3;

    public Account() {
    }

    public Account(String address, String birthDate, boolean gender, String name, String personalID, String phone, Vaccine vaccine1, Vaccine vaccine2, Vaccine vaccine3) {
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.name = name;
        this.personalID = personalID;
        this.phone = phone;
        this.vaccine1 = vaccine1;
        this.vaccine2 = vaccine2;
        this.vaccine3 = vaccine3;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Address", address);
        result.put("BirthDate", birthDate);
        result.put("Gender", gender);
        result.put("Name", name);
        result.put("PersonalID", personalID);
        result.put("Phone", phone);
        result.put("Vaccine1", vaccine1);
        result.put("Vaccine2", vaccine2);
        result.put("Vaccine3", vaccine3);

        return result;
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

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Vaccine getVaccine1() {
        return vaccine1;
    }

    public void setVaccine1(Vaccine vaccine1) {
        this.vaccine1 = vaccine1;
    }

    public Vaccine getVaccine2() {
        return vaccine2;
    }

    public void setVaccine2(Vaccine vaccine2) {
        this.vaccine2 = vaccine2;
    }

    public Vaccine getVaccine3() {
        return vaccine3;
    }

    public void setVaccine3(Vaccine vaccine3) {
        this.vaccine3 = vaccine3;
    }

    public String getPhoneVn(){
        return '0'+phone.substring(3);
    }
}