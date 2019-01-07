package com.example.chand.traveltogether.Utils;


import android.util.Base64;

public class CodeHelper {
    private static volatile CodeHelper codeHelper = new CodeHelper();


    public CodeHelper getCodeHelper(){
        synchronized (CodeHelper.class){
            return codeHelper;
        }
    }

    public String encode(String s){
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
    }

    public String decode(String s){
        return new String(Base64.decode(s.getBytes(), Base64.DEFAULT));
    }

}
