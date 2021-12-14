package com.example.mobilegreenfood.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TokenUser {
    static SharedPreferences mPrefs;
    static SharedPreferences.Editor editor;
    static String token;
    static Context context;

    public TokenUser(Context context) {
        this.context = context;
        mPrefs = context.getSharedPreferences("STORAGE_LOGIN_API", Context.MODE_PRIVATE);
        editor = mPrefs.edit();
    }




    public static String getToken() {
        token = mPrefs.getString("TOKEN", "");
        return token;
    }

    public static void setToken(String token) {
        editor.putString("TOKEN", token);
        editor.apply();
    }
}
