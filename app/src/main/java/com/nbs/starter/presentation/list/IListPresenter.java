package com.nbs.starter.presentation.list;


import com.nbs.starter.base.IBasePresenter;
import com.nbs.starter.base.IBaseView;
import com.nbs.starter.base.model.RequestModel;

public interface IListPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void loadMessage(RequestModel requestModel);

}
