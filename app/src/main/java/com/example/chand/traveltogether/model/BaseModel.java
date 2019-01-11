package com.example.chand.traveltogether.model;


public class BaseModel {
    private int resCode;
    private String resMsg;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "resCode=" + resCode +
                ", resMsg='" + resMsg + '\'' +
                '}';
    }
}
