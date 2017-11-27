package com.nbs.starter.base.adapter.viewholder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;


public abstract class BaseHeaderViewHolder extends BaseItemViewHolder {
    protected Context mContext;
    protected Bundle mBundle;

    public BaseHeaderViewHolder(Context context, View itemView, Bundle bundle) {
        super(context, itemView, null, null);
        this.mBundle = bundle;
    }

    @Override
    public void bind(Object o) {

    }

    public abstract void show();

    public void saveState(Bundle bundle) {
        this.mBundle = bundle;
    }
}
