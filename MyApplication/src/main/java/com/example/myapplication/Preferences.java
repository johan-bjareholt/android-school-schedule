package com.example.myapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;


/**
 * Created by johan on 8/22/13.
 */
public class Preferences extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
