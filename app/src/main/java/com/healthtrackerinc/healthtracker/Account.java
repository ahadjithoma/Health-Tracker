package com.healthtrackerinc.healthtracker;

/**
 * Created by andre on 21-Feb-17.
 */

public class Account {

    private static Account instance;

    public static Account getInstance() {
        if (instance == null)
            instance = new Account();
        return instance;
    }

    private Account() {
    }

    private int user, admin, pharmacy, doctor;

    public int getAdminValue() {
        return admin;
    }
    public void setAdminValue(int i) {
        this.admin = i;
    }

    public int getUserValue() {
        return user;
    }
    public void setUserValue(int i) {
        this.user = i;
    }

    public int getDoctorValue() {
        return doctor;
    }
    public void setDoctorValue(int i) {
        this.doctor = i;
    }

    public int getPharmacyValue() {
        return pharmacy;
    }
    public void setPharmacyValue(int i) {
        this.pharmacy = i;
    }

    public String getAccountType(int i){
        if (user==1)
            return "User";
        else if (admin==1)
            return "Admin";
        else if (doctor==1)
            return "Doctor";
        else if (pharmacy==1)
            return "Pharmacy";
        else
            return "";
    }
}