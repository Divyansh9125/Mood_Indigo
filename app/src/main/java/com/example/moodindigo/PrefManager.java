package com.example.moodindigo;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    int private_mode = 0;

    private static final String PREF_NAME ="MoodIndigo_prefs";
    private static final String IsFirstTimeLaunch = "IsFirstTimeLaunch";
    private static final String HasLoggedIn = "HasLoggedIn";

    public PrefManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, private_mode);
        editor = pref.edit();
    }

    public void setFirstLaunch(boolean isFirstLaunch){
        editor.putBoolean(IsFirstTimeLaunch, isFirstLaunch);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IsFirstTimeLaunch, true);
    }

    public void setHasLoggedIn(boolean hasloogedin){
        editor.putBoolean(HasLoggedIn, hasloogedin);
        editor.commit();
    }

    public boolean hasLoggedIn(){
        return pref.getBoolean(HasLoggedIn, false);
    }
}
