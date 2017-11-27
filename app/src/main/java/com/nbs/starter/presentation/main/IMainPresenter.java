package com.nbs.starter.presentation.main;

import com.nbs.starter.base.IBasePresenter;
import com.nbs.starter.base.IBaseView;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public interface IMainPresenter <V extends IBaseView> extends IBasePresenter<V> {

    void loadData();

}
