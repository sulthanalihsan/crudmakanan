package com.catatanasad.crudmakanan;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static String KEY_EMAIL = "email";
    public static String KEY_NAME = "name";
    public static String SHARED_PREF_NAME = "login";

    public static void saveDataLogin(Context context, String email, String nama){

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, nama);
        editor.apply();
    }

    public static void removeDataLogin(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    public static String getDataEmail(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public static String getDataName(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null);
    }
}