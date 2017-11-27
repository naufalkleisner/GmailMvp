package com.nbs.starter.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nbs.starter.base.adapter.viewholder.BaseItemViewHolder;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRecyclerAdapter<Data, Holder extends BaseItemViewHolder> extends
        RecyclerView.Adapter<Holder> {
    protected Context mContext;
    List<Data> mDatas;
    protected OnItemClickListener mItemClickListener;
    protected OnLongItemClickListener mLongItemClickListener;

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }

    public BaseRecyclerAdapter(Context context, List<Data> data) {
        this.mContext = context;
        this.mDatas = data;
    }

    protected View getView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mContext).inflate(getItemResourceLayout(viewType), parent, false);
    }

    protected abstract int getItemResourceLayout(int viewType);

    @Override
    public abstract Holder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return mDatas.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnLongItemClickListener(OnLongItemClickListener longItemClickListener) {
        this.mLongItemClickListener = longItemClickListener;
    }

    public List<Data> getDatas() {
        return mDatas;
    }

    public void add(Data item) {
        mDatas.add(item);
        notifyItemInserted(mDatas.size() - 1);
    }

    public void addAll(List<Data> items) {
        add(items);
    }

    public void add(Data item, int position) {
        mDatas.add(position, item);
        notifyItemInserted(position);
    }

    public void add(final List<Data> items) {
        final int size = items.size();
        for (int i = 0; i < size; i++) {
            mDatas.add(items.get(i));
        }
        notifyDataSetChanged();
    }

    public void addOrUpdate(Data item) {
        int i = mDatas.indexOf(item);
        if (i >= 0) {
            mDatas.set(i, item);
            notifyItemChanged(i);
        } else {
            add(item);
        }
    }

    public void addOrUpdate(final List<Data> items) {
        final int size = items.size();
        for (int i = 0; i < size; i++) {
            Data item = items.get(i);
            int x = mDatas.indexOf(item);
            if (x >= 0) {
                mDatas.set(x, item);
            } else {
                add(item);
            }
        }
        notifyDataSetChanged();
    }


    public void addOrUpdateToFirst(final List<Data> items) {
        final int size = items.size();
        for (int i = 0; i < size; i++) {
            Data item = items.get(i);
            int x = mDatas.indexOf(item);
            if (x >= 0) {
                mDatas.set(x, item);
            } else {
                add(item, 0);
            }
        }
        notifyDataSetChanged();
    }

    public void addToFirst(Data item) {
        mDatas.add(0, item);
        notifyDataSetChanged();
    }

    public void addToFirst(final List<Data> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (position >= 0 && position < mDatas.size()) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void remove(Data item) {
        int position = mDatas.indexOf(item);
        remove(position);
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnLongItemClickListener {
        void onLongItemClick(View view, int position);
    }
}
