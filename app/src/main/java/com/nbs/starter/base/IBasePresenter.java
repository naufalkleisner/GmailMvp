package com.nbs.starter.base;

import com.nbs.starter.data.api.model.error.ApiError;

public interface IBasePresenter<T extends IBaseView> {

    void onAttach(T view);

    void onDetach();

    void handleApiError(ApiError error);

}