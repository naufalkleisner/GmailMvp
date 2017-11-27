package com.nbs.starter.di.modules;

import android.app.Activity;
import android.content.Context;

import com.nbs.starter.presentation.detail.DetailPresenter;
import com.nbs.starter.presentation.detail.DetailView;
import com.nbs.starter.presentation.detail.IDetailPresenter;
import com.nbs.starter.presentation.list.IListPresenter;
import com.nbs.starter.presentation.list.ListPresenter;
import com.nbs.starter.presentation.list.ListView;
import com.nbs.starter.presentation.main.IMainPresenter;
import com.nbs.starter.presentation.main.MainPresenter;
import com.nbs.starter.presentation.main.MainView;
import com.nbs.starter.presentation.splash.ISplashPresenter;
import com.nbs.starter.presentation.splash.SplashPresenter;
import com.nbs.starter.presentation.splash.SplashView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ghiyatshanif on 2/21/17.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity(Activity mActivity) {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    IMainPresenter<MainView> provideMainPresenter(MainPresenter<MainView> mainPresenter){
        return mainPresenter;
    }

    @Provides
    ISplashPresenter<SplashView> provideSplashPresenter(SplashPresenter<SplashView> splashPresenter){
        return splashPresenter;
    }
    @Provides
    IDetailPresenter<DetailView> provideDetailPresenter(DetailPresenter<DetailView> detailPresenter){
        return  detailPresenter;
    }

    @Provides
    IListPresenter<ListView> provideListPresenter(ListPresenter<ListView> listPresenter){
        return  listPresenter;
    }
}
