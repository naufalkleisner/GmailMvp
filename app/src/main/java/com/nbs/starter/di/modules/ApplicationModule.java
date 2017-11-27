package com.nbs.starter.di.modules;

import android.app.Application;
import android.content.Context;

import com.nbs.starter.di.qualifiers.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ghiyatshanif on 2/21/17.
 */
@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @ApplicationContext
    @Provides
    Context provideApplicationContext(){
        return mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }



}
