package com.metalinko.fyp.Model;

public class SecurityGardCallModel {

    private String sd_name , phone_number;

    public String getSd_name() {
        return sd_name;
    }


    public SecurityGardCallModel()
    {

        // Emoty
    }
    public void setSd_name(String sd_name) {
        this.sd_name = sd_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public SecurityGardCallModel(String sd_name, String phone_number) {
        this.sd_name = sd_name;
        this.phone_number = phone_number;
    }
}
