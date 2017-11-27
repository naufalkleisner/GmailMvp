package com.nbs.starter.data.api.model.error;

/**
 * Created by ghiyatshanif on 4/23/17.
 */

public interface ErrorListener {
    void onBadRequestError(String message);

    void onUnauthorizeRequestError(String message);

    void onForbiddenRequestError(String message);

    void onNotFoundError(String message);

    void onTimeoutError();

    void onNoInternetError();

    void onUnknownError();

    void onJsonSyntaxException(String cause);

}
