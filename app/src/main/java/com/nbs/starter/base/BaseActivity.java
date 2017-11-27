
package com.nbs.starter.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.nbs.starter.R;
import com.nbs.starter.di.components.ActivityComponent;
import com.nbs.starter.di.components.DaggerActivityComponent;
import com.nbs.starter.di.modules.ActivityModule;
import com.nbs.starter.events.UserUnauthorizedEvent;
import com.nbs.starter.presentation.splash.SplashScreenActivity;
import com.nbs.starter.utils.MessageFactory;
import com.nbs.starter.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity
        implements IBaseView, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    private EventBus eventBus;
    private BaseActivity eventBusSubscriber;

    private boolean shouldRegisterEventBus = false;
    private boolean unregisterEventBusOnStop = true;
    private boolean unregisterEventBusOnDestroy = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((BaseApplication) getApplication()).getComponent())
                .activityModule(new ActivityModule(this))
                .build();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(getLayoutResource());
        setUnbinder(ButterKnife.bind(this));

        onViewReady();
        // TODO: 6/5/17 set shouldRegisterEventbus to true if there are any public event to subscribe e.g. UserUnauthorizedEvent
    }


    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public void reInitializeApplicationComponent() {
        ((BaseApplication) getApplication()).reInitializeComponent();
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void setupToolbar(Toolbar toolbar, String title, boolean isChild) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isChild);
        }
    }

    @Override
    public void setupToolbar(Toolbar toolbar, boolean isChild) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isChild);
        }
    }

    @Override
    public void setupToolbar(String title, boolean isChild) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isChild);
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = MessageFactory.showLoadingDialog(this, getString(R.string.please_wait));
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackbar(message);
        } else {
            showSnackbar(getString(R.string.error));
        }
    }

    @Override
    public void onError(int resourceString) {
        onError(getString(resourceString));
    }

    @Override
    public void showSnackbar(String message) {
        MessageFactory.showSnackbarMessage(findViewById(android.R.id.content), message);
    }

    @Override
    public void showToast(String message) {
        MessageFactory.showToast(message);
    }

    @Override
    public void showAlert(String message) {
        MessageFactory.showAlertDialog(this, message);
    }

    @Override
    public boolean isNetworkAvailable() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder iBinder = null;
        try {
            iBinder = getCurrentFocus().getWindowToken();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (iBinder != null) {
            inputManager.hideSoftInputFromWindow(iBinder,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            try {
                mUnBinder.unbind();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        if (unregisterEventBusOnDestroy)
            unRegisterEventBus();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if (unregisterEventBusOnStop)
            unRegisterEventBus();
        super.onStop();
    }

    private void registerEventBus() {
        if (shouldRegisterEventBus) {
            if (eventBus == null && eventBusSubscriber == null) {
                eventBus = EventBus.getDefault();
                eventBusSubscriber = BaseActivity.this;
            }

            if (!eventBus.isRegistered(eventBusSubscriber) && !(BaseActivity.this instanceof SplashScreenActivity))
                eventBus.register(eventBusSubscriber);
        }
    }


    private void unRegisterEventBus() {
        if (eventBus != null && eventBusSubscriber != null) {
            eventBus.unregister(eventBusSubscriber);
        }
    }

    public void setFragment(int viewRes, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(viewRes, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEventBus();
    }

    private void setUnbinder(Unbinder unbinder) {
        this.mUnBinder = unbinder;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserUnauthorizedEvent event) {

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
}