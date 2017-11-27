package com.nbs.starter.presentation.list;


import com.nbs.starter.base.BasePresenter;
import com.nbs.starter.base.model.RequestModel;
import com.nbs.starter.data.DataManager;
import com.nbs.starter.data.api.model.Message;
import com.nbs.starter.domain.MessageUseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ListPresenter<V extends ListView> extends BasePresenter<V> implements IListPresenter<V>
        , MessageUseCase.Callback{

    MessageUseCase messageUseCase;

    @Inject
    public ListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
        messageUseCase = new MessageUseCase(dataManager,this);
        getCompositeUseCase().add(messageUseCase);
    }

    @Override
    public void loadMessage(RequestModel requestModel) {
        getView().showLoading();
        messageUseCase.setRequestModel(requestModel);
        messageUseCase.execute();

    }

    @Override
    public void onGetMessageSucces(ArrayList<Message> response){
        getView().hideLoading();
        getView().showMessage(response);

    }

    @Override
    public void onGetMessageFailed(String message){
        getView().hideLoading();
        getView().showToast(message);

    }
}
