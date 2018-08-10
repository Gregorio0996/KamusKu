package com.example.gregorio.kamusku;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;

public class AppPreference {
    SharedPreferences sharedPreferences;
    Context context;

    public AppPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Pertama Kali Jalan", input);
        editor.commit();
    }

    public Boolean getFirstRun() {
        return sharedPreferences.getBoolean("Pertama Kali Jalan", true);
    }
}
