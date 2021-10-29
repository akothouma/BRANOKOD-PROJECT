package com.example.newu.branokod3;

class Profiledetails {
    String regno;
    String cam;
    String phone;
    String uri;

    public  Profiledetails(){}

    public Profiledetails(String registrationnum, String campus, String contact, String toString) {
        regno=registrationnum;
        cam=campus;
        phone=contact;
        uri=toString;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getCam() {
        return cam;
    }

    public void setCam(String cam) {
        this.cam = cam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
