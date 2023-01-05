package com.metalinko.fyp.Model;

public class StudentBusModel {


   private String Full_Name, System_id , Course, Samester , Shift, Challan_Form , Bus_No , Shop_Number;

   public StudentBusModel() {

      // Empty
   }


   public StudentBusModel(String full_Name, String system_id, String course, String samester, String shift, String challan_Form, String bus_No, String shop_Number) {
      Full_Name = full_Name;
      System_id = system_id;
      Course = course;
      Samester = samester;
      Shift = shift;
      Challan_Form = challan_Form;
      Bus_No = bus_No;
      Shop_Number = shop_Number;
   }

   public String getFull_Name() {
      return Full_Name;
   }

   public void setFull_Name(String full_Name) {
      Full_Name = full_Name;
   }

   public String getSystem_id() {
      return System_id;
   }

   public void setSystem_id(String system_id) {
      System_id = system_id;
   }

   public String getCourse() {
      return Course;
   }

   public void setCourse(String course) {
      Course = course;
   }

   public String getSamester() {
      return Samester;
   }

   public void setSamester(String samester) {
      Samester = samester;
   }

   public String getShift() {
      return Shift;
   }

   public void setShift(String shift) {
      Shift = shift;
   }

   public String getChallan_Form() {
      return Challan_Form;
   }

   public void setChallan_Form(String challan_Form) {
      Challan_Form = challan_Form;
   }

   public String getBus_No() {
      return Bus_No;
   }

   public void setBus_No(String bus_No) {
      Bus_No = bus_No;
   }

   public String getShop_Number() {
      return Shop_Number;
   }

   public void setShop_Number(String shop_Number) {
      Shop_Number = shop_Number;
   }
}
