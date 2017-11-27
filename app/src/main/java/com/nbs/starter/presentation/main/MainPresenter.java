package com.nbs.starter.presentation.main;

import com.nbs.starter.base.BasePresenter;
import com.nbs.starter.base.model.RequestModel;
import com.nbs.starter.data.DataManager;
import com.nbs.starter.data.api.model.response.SampleResponse;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements IMainPresenter<V> {


    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
        RequestModel requestModel = new RequestModel();
    }

    @Override
    public void loadData() {
    }

    public void onSuccess(SampleResponse response) {
        getView().showToast("Yeah berhasiiil");
    }

    public void onFailed(String Message) {
        getView().showToast("Cupu bat luu!");
    }
}
