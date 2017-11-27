package com.nbs.starter.di.modules;

import com.nbs.starter.BuildConfig;
import com.nbs.starter.data.api.ApiManager;
import com.nbs.starter.data.api.IApiManager;
import com.nbs.starter.data.api.libs.ApiClient;
import com.nbs.starter.data.api.libs.ApiService;
import com.nbs.starter.data.api.libs.HeaderInterceptor;
import com.nbs.starter.data.api.libs.OkHttpClientFactory;
import com.nbs.starter.data.api.libs.ParameterInterceptor;
import com.nbs.starter.data.preference.IPreferenceManager;

import java.util.HashMap;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Module
public class ApiModule {


    @Named("accessToken")
    @Provides
    String provideAccessToken(IPreferenceManager preferenceManager) {
        return preferenceManager.getAccessToken();
    }

    @Provides
    HeaderInterceptor provideHeaderInterceptor() {
//        provide default header (if needed) to hashmap
        HashMap<String, String> headers = new HashMap<>();

        return new HeaderInterceptor(headers);
    }

    @Provides
    ParameterInterceptor provideParameterInterceptor(@Named("accessToken") String accessToken) {
//        put all default parameter like apikey to hashmap
        HashMap<String, String> params = new HashMap<>();
        if (!"".equals(accessToken)) {
            params.put("access_token", accessToken);
        }
        return new ParameterInterceptor(params);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkhttpClient(HeaderInterceptor headerInterceptor, ParameterInterceptor parameterInterceptor) {
        return OkHttpClientFactory.create(headerInterceptor, parameterInterceptor);
    }

    @Singleton
    @Provides
    String provideBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Singleton
    @Provides
    ApiClient provideApiClient(OkHttpClient okHttpClient, String url) {
        return ApiService.createService(ApiClient.class, okHttpClient, url);
    }

    @Singleton
    @Provides
    IApiManager provideApiManager(ApiManager apiManager) {
        return apiManager;
    }

}
