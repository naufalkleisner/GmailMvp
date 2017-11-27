package com.nbs.starter.presentation.detail;


import com.nbs.starter.base.BasePresenter;
import com.nbs.starter.data.DataManager;
import com.nbs.starter.data.api.model.Message;
import com.nbs.starter.domain.MessageUseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailPresenter<V extends DetailView> extends BasePresenter<V> implements IDetailPresenter<V>, MessageUseCase.Callback {

    MessageUseCase messageUseCase;
    @Inject
    public DetailPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
        messageUseCase = new MessageUseCase(dataManager, this);
    }

    @Override
    public void onGetMessageSucces(ArrayList<Message> messages) {
    }

    @Override
    public void onGetMessageFailed(String message) {

    }
}
