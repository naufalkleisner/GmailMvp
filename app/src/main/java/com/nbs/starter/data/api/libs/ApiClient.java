package com.nbs.starter.data.api.libs;

import com.nbs.starter.data.api.model.Message;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose: put all api calls here
 */

public interface ApiClient {

    // TODO: 3/3/17 change this as desired request

//       @GET("inbox.json")
//    Call<List<Message>> getInbox();

    @GET("inbox.json")
    Call<ArrayList<Message>> getInbox();
//    Call<MessageResponse> getInbox();
//    @GET("58fc801d0f00004lf11513615")
//    Call<SampleResponse> oadAnotherSample();
}
