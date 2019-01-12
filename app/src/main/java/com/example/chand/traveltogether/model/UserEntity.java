package com.example.chand.traveltogether.model;

public class UserEntity {
    private String account;
    private String name;
    private Integer gender;
    private Integer age;
    private Integer in;
    private Integer activity_id;
    private String city;
    private Integer status;
    private String code;
    private String passwd;
    private Integer num_Of_score;
    private Integer score;
    private String school;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getNum_Of_score() {
        return num_Of_score;
    }

    public void setNum_Of_score(Integer num_Of_score) {
        this.num_Of_score = num_Of_score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", in=" + in +
                ", activity_id=" + activity_id +
                ", city='" + city + '\'' +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", passwd='" + passwd + '\'' +
                ", num_Of_score=" + num_Of_score +
                ", score=" + score +
                ", school='" + school + '\'' +
                '}';
    }
}
