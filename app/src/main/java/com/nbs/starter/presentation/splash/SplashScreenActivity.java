package com.nbs.starter.presentation.splash;

import com.nbs.starter.R;
import com.nbs.starter.base.BaseActivity;

import javax.inject.Inject;

public class SplashScreenActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter<SplashView> splashPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(SplashScreenActivity.this);
        splashPresenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {

    }

    @Override
    public void toMainActivity() {

    }
}