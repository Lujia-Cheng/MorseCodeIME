package com.example.morsecodeime;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class MorseCodeIMESettings extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
