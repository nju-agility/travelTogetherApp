package com.example.chand.traveltogether.model;

import java.io.Serializable;

public class ArticalEntity implements Serializable {
    private int id;
    private String account;
    private String city;
    private String location;
    private String title;
    private String details;
    private String submission_date;
    private String imgPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(String submission_date) {
        this.submission_date = submission_date;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "ArticalEntity{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", submission_date='" + submission_date + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
