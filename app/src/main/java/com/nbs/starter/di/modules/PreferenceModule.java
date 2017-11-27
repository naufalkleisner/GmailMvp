package com.nbs.starter.di.modules;

import com.nbs.starter.data.preference.IPreferenceManager;
import com.nbs.starter.data.preference.PreferenceManager;
import com.nbs.starter.di.qualifiers.PreferenceInfo;
import com.nbs.starter.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Module
public class PreferenceModule {

    @PreferenceInfo
    @Provides
    String providePrefname() {
        return AppConstants.PREF_NAME;
    }

    @Singleton
    @Provides
    IPreferenceManager providePreferenceManager(PreferenceManager preferenceManager) {
        return preferenceManager;
    }
}
