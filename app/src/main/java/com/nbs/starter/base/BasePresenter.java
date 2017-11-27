package com.nbs.starter.base;

import com.nbs.starter.data.DataManager;
import com.nbs.starter.data.api.model.error.ApiError;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private final DataManager mDataManager;
    private final CompositeDisposable mCompositeDisposable;
    private ArrayList<UseCase> compositeUseCase;
    private V mView;

    @Inject
    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mCompositeDisposable = compositeDisposable;
        compositeUseCase = new ArrayList<UseCase>();
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        if (compositeUseCase.size() > 0)
            dispose(compositeUseCase);
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ArrayList<UseCase> getCompositeUseCase() {
        return compositeUseCase;
    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(IBaseView) before" +
                    " requesting data to the Presenter");
        }
    }

    @Override
    public void handleApiError(ApiError error) {

    }

    protected void dispose(ArrayList<UseCase> compositeUseCase) {
        for (UseCase usecase : compositeUseCase) {
            usecase.cancel();
        }
    }
}