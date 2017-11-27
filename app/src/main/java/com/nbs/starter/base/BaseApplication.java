package com.nbs.starter.base;

import android.app.Application;

import com.nbs.starter.BuildConfig;
import com.nbs.starter.di.components.ApplicationComponent;
import com.nbs.starter.di.components.DaggerApplicationComponent;
import com.nbs.starter.di.modules.ApplicationModule;
import com.nbs.starter.utils.ContextProvider;

import timber.log.Timber;

/**
 * Created by ghiyatshanif on 2/21/17.
 */

public class BaseApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ContextProvider.initialize(getApplicationContext());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);
    }

    public void reInitializeComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent mApplicationComponent) {
        this.mApplicationComponent = mApplicationComponent;
    }
}
