package com.nbs.starter.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nbs.starter.di.components.ActivityComponent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements IBaseView {

    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    private EventBus eventBus;
    private Fragment eventBusSubscriber;

    private boolean shouldRegisterEventBus = false;
    private boolean unregisterEventBusOnStop = true;
    private boolean unregisterEventBusOnDestroy = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        setUnBinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onViewReady();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void setupToolbar(Toolbar toolbar, String title, boolean isChild) {
        mActivity.setupToolbar(toolbar, title, isChild);
    }

    @Override
    public void setupToolbar(String title, boolean isChild) {
        mActivity.setupToolbar(title, isChild);
    }

    @Override
    public void setupToolbar(Toolbar toolbar, boolean isChild) {
        mActivity.setupToolbar(toolbar, isChild);
    }

    @Override
    public void showAlert(String message) {
        mActivity.showAlert(message);
    }

    @Override
    public void showToast(String message) {
        mActivity.showToast(message);
    }

    @Override
    public void showSnackbar(String message) {
        mActivity.showSnackbar(message);
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onError(int resourceString) {
        mActivity.onError(resourceString);
    }

    @Override
    public boolean isNetworkAvailable() {
        return mActivity.isNetworkAvailable();
    }

    @Override
    public void finishActivity() {
        mActivity.finishActivity();
    }

    @Override
    public void hideKeyboard() {
        mActivity.hideKeyboard();
    }

    public ActivityComponent getActivityComponent() {
        return mActivity.getActivityComponent();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    private void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }


    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        unRegisterEventBus();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        registerEventBus();
    }

    private void registerEventBus() {
        if (shouldRegisterEventBus) {
            if (eventBus == null && eventBusSubscriber == null) {
                eventBus = EventBus.getDefault();
                eventBusSubscriber = BaseFragment.this;
            }

            if (!eventBus.isRegistered(eventBusSubscriber))
                eventBus.register(eventBusSubscriber);
        }
    }

    private void unRegisterEventBus() {
        if (eventBus != null && eventBusSubscriber != null) {
            eventBus.unregister(eventBusSubscriber);
        }
    }

    public void setFragmentEventBus(EventBus eventBus, Fragment subscriber) {
        if (this.eventBus == null && this.eventBusSubscriber == null) {
            this.eventBus = eventBus;
            this.eventBusSubscriber = subscriber;
        }
    }

    public boolean isUnregisterEventBusOnStop() {
        return unregisterEventBusOnStop;
    }

    public void setUnregisterEventBusOnStop(boolean unregisterEventBusOnStop) {
        this.unregisterEventBusOnStop = unregisterEventBusOnStop;
    }

    public boolean isUnregisterEventBusOnDestroy() {
        return unregisterEventBusOnDestroy;
    }

    public void setUnregisterEventBusOnDestroy(boolean unregisterEventBusOnDestroy) {
        this.unregisterEventBusOnDestroy = unregisterEventBusOnDestroy;
        this.unregisterEventBusOnStop = false;
    }

    public boolean isShouldRegisterEventBus() {
        return shouldRegisterEventBus;
    }

    public void shouldRegisterEventBus(boolean shouldRegisterEventBus) {
        this.shouldRegisterEventBus = shouldRegisterEventBus;
    }


    private void onViewReady() {
        initLib();
        initIntent();
        initUI();
        initAction();
        initProcess();
    }


    //    pass Layout here
    protected abstract int getLayoutResource();

    //    Init Presenter and Component Injection here
    protected abstract void initLib();

    //    Extract desired intent here
    protected abstract void initIntent();

    //    initialize the UI, setup toolbar, setText etc here
    protected abstract void initUI();

    //    initialize UI interaction here
    protected abstract void initAction();

    //    initialize main Process here e.g call presenter to load data
    protected abstract void initProcess();


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}