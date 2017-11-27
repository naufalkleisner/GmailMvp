package com.nbs.starter.data.api.model.error;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * Created by ghiyatshanif on 4/10/17.
 */

public class ErrorResponse {
    @SerializedName("error")
    private ApiError error;

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error=" + error +
                '}';
    }

    public static String getErrorMessage(ResponseBody errorBody) {
        try {
            String errorResponse = errorBody.string();
            Timber.d("getErrorMessage(): " + errorResponse);
            return new Gson().fromJson(errorResponse, ErrorResponse.class).getError().getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
