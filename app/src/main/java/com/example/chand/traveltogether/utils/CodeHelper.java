package com.example.chand.traveltogether.utils;


import android.content.Context;
import android.util.Base64;

import com.bumptech.glide.Glide;

public class CodeHelper {
    private static CodeHelper codeHelper = new CodeHelper();


    public static CodeHelper getCodeHelper(){
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

    public static void clear(Context context){
        Glide.get(context).clearMemory();
//        Glide.get(context).clearDiskCache();
    }

}
