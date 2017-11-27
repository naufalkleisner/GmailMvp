package com.nbs.starter.presentation.main;

import android.content.Context;
import android.content.Intent;

import com.nbs.starter.R;
import com.nbs.starter.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    IMainPresenter<MainView> mainPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(MainActivity.this);
        mainPresenter.onAttach(this);
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
        mainPresenter.loadData();
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }
}
