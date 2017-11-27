package com.nbs.starter.data.api;

import com.nbs.starter.data.api.libs.ApiClient;
import com.nbs.starter.data.api.model.Message;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class ApiManager implements IApiManager {
    private ApiClient mApiClient;

    @Inject
    public ApiManager(ApiClient mApiClient) {
        this.mApiClient = mApiClient;
    }


    @Override
    public Call<ArrayList<Message>> getInbox() {
        return mApiClient.getInbox();
    }



//    @Override
//    public Call<SampleResponse> loadAnotherSample() {
//        return mApiClient.loadAnotherSample();
//    }
}
