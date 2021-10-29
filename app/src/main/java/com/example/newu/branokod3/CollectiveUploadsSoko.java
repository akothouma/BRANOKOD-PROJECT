package com.example.newu.branokod3;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

class CollectiveUploadsSoko {
    String des;
    String expo;
    String contact;
    String type;
    String posttime;
    String uri;
public CollectiveUploadsSoko(){}

    public CollectiveUploadsSoko(String des, String expo, String contact, String type, String posttime, String uri) {
        this.des = des;
        this.expo = expo;
        this.contact = contact;
        this.type = type;
        this.posttime = posttime;
        this.uri = uri;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getExpo() {
        return expo;
    }

    public void setExpo(String expo) {
        this.expo = expo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}