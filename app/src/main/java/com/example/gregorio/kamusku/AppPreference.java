package com.example.gregorio.kamusku;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;

public class AppPreference {

    private SharedPreferences sharedPreferences;
    AppPreference(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    void setFirstRun(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Pertama Kali Jalan", false);
        editor.apply();
    }

    Boolean getFirstRun(){
        return sharedPreferences.getBoolean("Pertama Kali Jalan", true);
    }
}
