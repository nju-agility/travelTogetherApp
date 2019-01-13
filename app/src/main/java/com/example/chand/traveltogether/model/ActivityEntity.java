package com.example.chand.traveltogether.model;

import java.io.Serializable;

public class ActivityEntity implements Serializable {
    private Integer aid;
    private Integer status;
    private String owner;
    private String city;
    private String location;
    private String title;
    private String details;
    private String time_start;
    private String time_end;
    private Integer score;
    private Integer num_of_score;
    private String type;
    private int price;
    private String activityURL;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getActivityURL() {
        return activityURL;
    }

    public void setActivityURL(String activityURL) {
        this.activityURL = activityURL;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getNum_of_score() {
        return num_of_score;
    }

    public void setNum_of_score(Integer num_of_score) {
        this.num_of_score = num_of_score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ActivityEntity{" +
                "aid=" + aid +
                ", status=" + status +
                ", owner='" + owner + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", time_start='" + time_start + '\'' +
                ", time_end='" + time_end + '\'' +
                ", score=" + score +
                ", num_of_score=" + num_of_score +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", activityURL='" + activityURL + '\'' +
                '}';
    }
}
