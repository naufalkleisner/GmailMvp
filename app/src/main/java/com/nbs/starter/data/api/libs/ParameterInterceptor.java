package com.nbs.starter.data.api.libs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ghiyatshanif on 2/27/17.
 * purpose : put all default parameter that you want to be add to every request here e.g access token
 */

public class ParameterInterceptor implements Interceptor {

    private HashMap<String, String> params;

    public ParameterInterceptor(HashMap<String, String> params) {
        this.params = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .url(mapParameters(chain));

        Request request = requestBuilder.build();
        return chain.proceed(request);

    }

    private HttpUrl mapParameters(Chain chain) {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl.Builder builder = originalHttpUrl.newBuilder();

        for (Entry<String, String> entry : params.entrySet()) {
            builder.addQueryParameter(entry.getKey(),entry.getValue());
        }

        return builder.build();
    }
}
