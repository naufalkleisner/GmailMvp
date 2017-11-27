package com.nbs.starter.di.components;

import android.app.Application;
import android.content.Context;

import com.nbs.starter.base.BaseApplication;
import com.nbs.starter.data.DataManager;
import com.nbs.starter.di.qualifiers.ApplicationContext;
import com.nbs.starter.di.modules.ApiModule;
import com.nbs.starter.di.modules.ApplicationModule;
import com.nbs.starter.di.modules.DbModule;
import com.nbs.starter.di.modules.PreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Singleton
@Component (modules = {ApplicationModule.class, ApiModule.class, DbModule.class, PreferenceModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication baseApplication);

    DataManager getDataManager();


    @ApplicationContext
    Context getContext();

    Application getApplication();

}
