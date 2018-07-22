package com.example.denny.booklister;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference);

        //get the istance of the preferences
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        //get an istance of PreferenceScreen to count the number of shared preferences as well
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int preferenceCount =preferenceScreen.getPreferenceCount();

        //iterate to all my preferences with a for-cycle
        for(int i=0;i<preferenceCount;i++){
            Preference preference = preferenceScreen.getPreference(i);
            if(!(preference instanceof CheckBoxPreference)){
                //get the value and pass it to the PreferenceSummary method
                String value = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference,value);
            }

        }
    }

    private void setPreferenceSummary(Preference preference, String value){
        //check if the preference received is an istance of list preference
        if(preference instanceof ListPreference){
            //cast the preference into a listPreference type
            ListPreference listPreference = (ListPreference) preference;
            //find the value of the selected preference
            int indexValue =listPreference.findIndexOfValue(value);
            // check if the index is valid
            if(indexValue >=0){
                //if the index is valied add the value to the summary
                listPreference.setSummary(listPreference.getEntries()[indexValue]);
                //the code above get compare the entries with the indexValue
            }
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        //find the preference s (key) that has been changing
        Preference preference = findPreference(s);
        if(preference != null){
            //check also if the preference is not the checkbox
            if(!(preference instanceof CheckBoxPreference)){
                //get the values changes and update the summary
                String value = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference,value);
            }
        }
    }

    /*
    For listeners is important to register them onCreate and Unregister them onDestroy
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
