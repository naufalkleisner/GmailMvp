package com.nbs.starter.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.nbs.starter.di.qualifiers.ApplicationContext;
import com.nbs.starter.di.qualifiers.PreferenceInfo;

import javax.inject.Inject;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose : Application preference class
 */

public class PreferenceManager implements IPreferenceManager{

//    put all preference keys here
    private static final String KEY_PREF_ACCESS_TOKEN = "KEY_PREF_ACCESS_TOKEN";
    private static final String KEY_PREF_USERNAME = "KEY_PREF_USERNAME";


    private SharedPreferences mPreferences;

    @Inject
    public PreferenceManager(@ApplicationContext Context context,
                             @PreferenceInfo String prefName){
        mPreferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferences.edit().putString(KEY_PREF_ACCESS_TOKEN,accessToken).apply();
    }

    @Override
    public String getAccessToken() {
        return mPreferences.getString(KEY_PREF_ACCESS_TOKEN,"");
    }

    @Override
    public void setUsername(String username) {
        mPreferences.edit().putString(KEY_PREF_USERNAME, username).apply();
    }

    @Override
    public String getUsername() {
        return mPreferences.getString(KEY_PREF_USERNAME,"");
    }
}
