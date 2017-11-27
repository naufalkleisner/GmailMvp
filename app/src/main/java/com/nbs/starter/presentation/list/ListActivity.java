package com.nbs.starter.presentation.list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbs.starter.R;
import com.nbs.starter.base.BaseActivity;
import com.nbs.starter.base.adapter.BaseRecyclerAdapter;
import com.nbs.starter.base.model.RequestModel;
import com.nbs.starter.data.api.model.Message;
import com.nbs.starter.presentation.adapter.MessageAdapter;
import com.nbs.starter.presentation.detail.DetailActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends BaseActivity implements ListView, SwipeRefreshLayout.OnRefreshListener  {
    @Inject
    IListPresenter<ListView> presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private MessageAdapter messageAdapter;
    private  ArrayList<Message> messages;

    public static void start(Context context) {
        Intent starter = new Intent(context, ListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    //    Init Presenter and Component Injection here
    @Override
    protected void initLib() {
        getActivityComponent().inject(ListActivity.this);
        presenter.onAttach(this);

    }

    //    Extract desired intent here
    @Override
    protected void initIntent() {

    }

    //    initialize the UI, setup toolbar, setText etc here
    @Override
    protected void initUI() {
        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(this,messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(messageAdapter);


    }

    //    initialize UI interaction here
    @Override
    protected void initAction() {
            messageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    DetailActivity.start(ListActivity.this, messageAdapter.getDatas().get(position));
                }
            });



    }

    //    initialize main Process here e.g call presenter to load data
    @Override
    protected void initProcess() {
        presenter.loadMessage(new RequestModel());

    }

    @Override
    public void showMessage(ArrayList<Message> response) {
        this.messages = response;
        messageAdapter.clear();
        if(response != null){
            messageAdapter.addOrUpdate(response);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        messageAdapter.clear();
        presenter.loadMessage(new RequestModel());
        swipeRefreshLayout.setRefreshing(false);

    }
}
