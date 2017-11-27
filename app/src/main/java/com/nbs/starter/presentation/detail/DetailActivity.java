package com.nbs.starter.presentation.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nbs.starter.R;
import com.nbs.starter.base.BaseActivity;
import com.nbs.starter.data.api.model.Message;
import com.nbs.starter.presentation.adapter.MessageAdapter;
import com.nbs.starter.utils.BundleKeys;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailView {
    @Inject
    IDetailPresenter<DetailView> presenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    @BindView(R.id.tv_isi)
    TextView tvIsi;

    private MessageAdapter messageAdapter;
    private Message message;

    public static void start(Context context, Message message) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(BundleKeys.KEY_MESSAGE, message);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }

    //    Init Presenter and Component Injection here
    @Override
    protected void initLib() {
        getActivityComponent().inject(DetailActivity.this);
        presenter.onAttach(this);

    }

    //    Extract desired intent here
    @Override
    protected void initIntent() {
        message = getIntent().getParcelableExtra(BundleKeys.KEY_MESSAGE);
    }

    //    initialize the UI, setup toolbar, setText etc here
    @Override
    protected void initUI() {

        tvTitle.setText(message.getFrom());
        tvSubtitle.setText(message.getSubject());
        tvSubtitle.setText(message.getMessage());


    }

    //    initialize UI interaction here
    @Override
    protected void initAction() {

    }

    //    initialize main Process here e.g call presenter to load data
    @Override
    protected void initProcess() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
