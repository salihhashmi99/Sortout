package com.metalinko.fyp.Model;

public class SuggestionFormModel {

   private  String  st_name, st_email,st_contact,st_title,st_dec;




   public SuggestionFormModel()
   {
       // Empty
   }

    public SuggestionFormModel(String st_name, String st_email, String st_contact, String st_title, String st_dec) {
        this.st_name = st_name;
        this.st_email = st_email;
        this.st_contact = st_contact;
        this.st_title = st_title;
        this.st_dec = st_dec;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_email() {
        return st_email;
    }

    public void setSt_email(String st_email) {
        this.st_email = st_email;
    }

    public String getSt_contact() {
        return st_contact;
    }

    public void setSt_contact(String st_contact) {
        this.st_contact = st_contact;
    }

    public String getSt_title() {
        return st_title;
    }

    public void setSt_title(String st_title) {
        this.st_title = st_title;
    }

    public String getSt_dec() {
        return st_dec;
    }

    public void setSt_dec(String st_dec) {
        this.st_dec = st_dec;
    }
}
