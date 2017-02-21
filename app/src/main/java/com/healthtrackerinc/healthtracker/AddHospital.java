package com.healthtrackerinc.healthtracker;

/**
 * Created by andre on 21-Feb-17.
 */

public class AddHospital {

    private static AddHospital instance;

    public static AddHospital getInstance() {
        if (instance == null)
            instance = new AddHospital();
        return instance;
    }

    private AddHospital() {
    }

    private String title, address, phone;
    private int add = 0;

    public int getAdd(){ return add; }
    public void setAdd(int i){ this.add = 1; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String s) { this.title = s; }

    public String getAddress() {
        return address;
    }
    public void setAddress(String s) { this.address = s; }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String s) { this.phone = s; }
}
