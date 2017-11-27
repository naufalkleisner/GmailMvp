package com.nbs.starter.presentation.list;


import com.nbs.starter.base.IBaseView;
import com.nbs.starter.data.api.model.Message;

import java.util.ArrayList;

public interface ListView extends IBaseView {
    void  showMessage(ArrayList<Message> response);

}
