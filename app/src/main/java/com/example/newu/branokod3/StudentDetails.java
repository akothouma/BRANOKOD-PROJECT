package com.example.newu.branokod3;

public class StudentDetails {

    String email1;
    String yearofCompletion;

    public StudentDetails(){}

    public StudentDetails(String email1, String yearofCompletion) {
        this.email1 = email1;
        this.yearofCompletion = yearofCompletion;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getYearofCompletion() {
        return yearofCompletion;
    }

    public void setYearofCompletion(String yearofCompletion) {
        this.yearofCompletion = yearofCompletion;
    }
}