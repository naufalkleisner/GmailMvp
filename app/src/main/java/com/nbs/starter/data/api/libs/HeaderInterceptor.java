package com.nbs.starter.data.api.libs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ghiyatshanif on 2/27/17.
 * purpose : put all default header that you want to be add to every request here e.g applicationType/apikey
 */

public class HeaderInterceptor implements Interceptor {
    private HashMap<String,String> headers;

    public HeaderInterceptor(HashMap<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = mapHeaders(chain);
        return chain.proceed(request);
    }

    private Request mapHeaders(Chain chain) {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder();

        for (Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(),entry.getValue());
        }
        return requestBuilder.build();
    }
}
