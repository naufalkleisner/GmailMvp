package com.nbs.starter.di.components;

import com.nbs.starter.di.qualifiers.PerActivity;
import com.nbs.starter.di.modules.ActivityModule;
import com.nbs.starter.presentation.detail.DetailActivity;
import com.nbs.starter.presentation.list.ListActivity;
import com.nbs.starter.presentation.main.MainActivity;
import com.nbs.starter.presentation.splash.SplashScreenActivity;

import dagger.Component;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@PerActivity
@Component (modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(SplashScreenActivity splashScreenActivity);

    void inject(ListActivity listActivity);

    void inject(DetailActivity detailActivity);
}
