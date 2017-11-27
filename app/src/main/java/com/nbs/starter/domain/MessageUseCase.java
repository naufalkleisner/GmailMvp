package com.nbs.starter.domain;

import com.nbs.starter.base.UseCase;
import com.nbs.starter.base.model.RequestModel;
import com.nbs.starter.data.DataManager;
import com.nbs.starter.data.api.model.Message;

import java.util.ArrayList;

import retrofit2.Call;

public class MessageUseCase extends UseCase<RequestModel, ArrayList<Message>> {

    Callback callback;

    public MessageUseCase(DataManager dataManager,Callback callback) {
        super(dataManager);
        this.callback = callback;
    }

    @Override
    protected Call<ArrayList<Message>> getApi() {
        if (getApiCall() == null){
            setApiCall(getDataManager().getInbox());
        }
        return getApiCall();
    }

    @Override
    protected ArrayList<Message> getCache(RequestModel request) {
        return null;
    }

    @Override
    protected void onCacheLoaded(ArrayList<Message> response) {

    }

    @Override
    protected void onResponseLoaded(ArrayList<Message> response) {
        callback.onGetMessageSucces(response);

    }



    @Override
    protected void onResponseEmpty() {

    }

    @Override
    protected void onErrorResponse(String message) {
        callback.onGetMessageFailed(message);

    }

    @Override
    protected void onRequestCancelled() {

    }


    public interface Callback {
        void onGetMessageSucces(ArrayList<Message> messages);

        void onGetMessageFailed(String message);
    }
}
