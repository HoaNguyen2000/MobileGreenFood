package com.example.mobilegreenfood.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TokenUser {
    public static final String PREFERENCE_NAME = "PREFERENCE_DATA";
    public static SharedPreferences sharedpreferences;
    public static String keyToken = "TOKEN_LOGIN";


    public TokenUser(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static String getToken() {
        return sharedpreferences.getString(keyToken, "NULL");
    }
    public static SharedPreferences.Editor editor = sharedpreferences.edit();
    public static void setToken(String token) {
        editor.putString(keyToken, token);
        editor.commit();
    }

    public static void clearToken() {
        editor.remove(keyToken);
        editor.commit();
    }
//    static SharedPreferences mPrefs;
//    static SharedPreferences.Editor editor;
//    static Context context;
//    static String keyToken = "TOKEN_LOGIN";
//
//    public TokenUser(Context context) {
//        this.context = context;
//        mPrefs = context.getSharedPreferences("STORAGE_LOGIN_API", Context.MODE_PRIVATE);
//        editor = mPrefs.edit();
//    }
//
//
//
//    public static String getToken() {
//        return mPrefs.getString(keyToken, "NULL");
//    }
//
//    public static void setToken(String token) {
//        editor.putString(keyToken, token);
//        editor.apply();
//    }
}
