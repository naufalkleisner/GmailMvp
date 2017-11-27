package com.nbs.starter.presentation.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbs.starter.R;
import com.nbs.starter.base.adapter.BaseRecyclerAdapter;
import com.nbs.starter.base.adapter.viewholder.BaseItemViewHolder;
import com.nbs.starter.data.api.model.Message;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Kleisner on 11/23/2017.
 */
public class MessageAdapter extends BaseRecyclerAdapter<Message, MessageAdapter.MessageViewHolder> {

    private Context mContext;
    private List<Message> messages;
    private MessageAdapterListener listener;
    private SparseBooleanArray selectedItems;

    //array used to perform multiple animation at once
    private SparseBooleanArray animationIntemsIndex;
    private boolean reverseAllAnimation = false;

    //index is used to animate only the selected row
    //dirty fix, find a better solution
    private  static  int currentSelectedIndex = -1;



    public MessageAdapter(Context context) {
        super(context);
    }

    public MessageAdapter(Context context, List<Message> data) {
        super(context, data);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.message_list_row;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    public class MessageViewHolder extends BaseItemViewHolder<Message> {

        @BindView(R.id.from)
        TextView tv_from;
        @BindView(R.id.txt_primary)
        TextView tv_txtPrimary;
        @BindView(R.id.txt_secondary)
        TextView tv_txtSecondary;
        @BindView(R.id.icon_profile)
        ImageView iv_iconProfile;
        @BindView(R.id.icon_text)
        TextView tv_iconText;
        @BindView(R.id.timestamp)
        TextView tv_timestamp;
        @BindView(R.id.icon_star)
        ImageView iv_iconStar;

        public MessageViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(Message data) {

            tv_from.setText(data.getFrom());
            tv_txtPrimary.setText(data.getSubject());
            tv_txtSecondary.setText(data.getMessage());
            tv_timestamp.setText(data.getTimestamp());
            String dataM =data.getFrom();
            Log.d("Adapter", "bind: "+dataM);

        }


    }
    public interface MessageAdapterListener {
        void onIconClicked(int position);

        void onIconImportantClicked(int position);

        void onMessageRowClicked(int position);

        void onRowLongClicked(int position);
    }
}