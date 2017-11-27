package com.nbs.starter.base;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.nbs.starter.base.model.RequestModel;
import com.nbs.starter.data.DataManager;
import com.nbs.starter.data.api.model.error.ErrorListener;
import com.nbs.starter.data.api.model.error.ErrorResponse;
import com.nbs.starter.events.UserUnauthorizedEvent;
import com.nbs.starter.utils.Message;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by ghiyatshanif on 4/17/17.
 */

public abstract class UseCase<T extends RequestModel, D> implements ErrorListener {

    private DataManager dataManager;
    private T requestModel;
    private Call<D> apiCall;


    public UseCase(DataManager dataManager, T requestModel) {
        this.dataManager = dataManager;
        this.requestModel = requestModel;
    }

    public UseCase(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public T getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(T requestModel) {
        this.requestModel = requestModel;
        apiCall = null;
    }

    public Call<D> getApiCall() {
        return apiCall;
    }

    public void setApiCall(Call<D> apiCall) {
        this.apiCall = apiCall;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    protected abstract Call<D> getApi();

    protected abstract D getCache(T request);

    protected abstract void onCacheLoaded(D response);

    protected abstract void onResponseLoaded(D response);

    protected abstract void onResponseEmpty();

    //General Error Response passed here
    protected abstract void onErrorResponse(String message);

    protected abstract void onRequestCancelled();

    public void execute() {
        if (getRequestModel() == null) {
            throw new IllegalStateException("Request Model cannot be null");
        }
        if (getCache(getRequestModel()) != null) {
            // TODO: 4/23/17 check if cache is valid and expiration here
            onCacheLoaded(getCache(getRequestModel()));
        } else {
            getApi().clone().enqueue(new Callback<D>() {
                @Override
                public void onResponse(Call<D> call, Response<D> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null && (response.code() != 204)) {
                            onResponseLoaded(response.body());
                        } else if (response.body() == null || response.code() == 204 ) {
                            onResponseEmpty();
                        }
                    } else {
                        String errorMessage = ErrorResponse.getErrorMessage(response.errorBody());
                        //catch general error
                        onErrorResponse(errorMessage);

                        //catch specific error
                        switch (response.code()) {
                            case 400:
                                onBadRequestError(errorMessage);
                                break;
                            case 401:
                                onUnauthorizeRequestError(errorMessage);
                                break;
                            case 403:
                                onForbiddenRequestError(errorMessage);
                                break;
                            case 404:
                                onNotFoundError(errorMessage);
                                break;
                            default:
                                break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<D> call, Throwable t) {
                    //check if failure cause is because request canceled
                    boolean isCancelled = "Canceled".equals(t.getMessage());

                    //catch general failure cause
                    if (isCancelled) {
                        onRequestCancelled();
                    } else {
                        onErrorResponse(Message.ERROR_UNABLE_TO_CONNECT_SERVER);
                    }


                    //catch specific failure cause
                    if (t instanceof SocketTimeoutException) {
                        onTimeoutError();
                    } else if (t instanceof IOException && !isCancelled) {
                        onNoInternetError();
                    } else if (t instanceof JsonSyntaxException) {
                        onJsonSyntaxException(t.getMessage());
                    } else {
                        if (!isCancelled) onUnknownError();
                    }
                }
            });
        }
    }

    public void cancel() {
        if (getApi().isExecuted()) {
            getApi().cancel();
        }
    }

    //    region spesific error
    @Override
    public void onBadRequestError(String message) {
        Timber.e("onBadRequestError(): " + message);
    }

    @Override
    public void onUnauthorizeRequestError(String message) {
        Timber.e("onUnauthorizeRequestError(): " + message);
        //check if user has accessToken, if so then consider accessToken that user posses has expired
        if (!TextUtils.isEmpty(getDataManager().getAccessToken())) {
            EventBus.getDefault().post(new UserUnauthorizedEvent());
            // TODO: 6/5/17 update/clear user preference (hard logout user)
        }
    }

    @Override
    public void onForbiddenRequestError(String message) {
        Timber.e("onForbiddenRequestError(): " + message);
    }

    @Override
    public void onNotFoundError(String message) {
        Timber.e("onNotFoundError(): " + message);
    }

    @Override
    public void onTimeoutError() {
        Timber.e("onTimeoutError(): ");
    }

    @Override
    public void onNoInternetError() {
        Timber.e("onNoInternetError(): ");
    }

    @Override
    public void onUnknownError() {
        Timber.e("onUnknownError(): ");
    }

    @Override
    public void onJsonSyntaxException(String cause) {
        Timber.e("onJsonSyntaxException(): " + cause);
    }

    //endregion
}
