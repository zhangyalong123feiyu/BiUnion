package com.bibinet.biunion.project.chatrow;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.ui.activity.ContextMenuActivity;
import com.bibinet.biunion.project.ui.fragment.CustomChatFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.widget.chatrow.ChatRow;
import com.hyphenate.helpdesk.model.MessageHelper;
import com.hyphenate.helpdesk.model.VisitorTrack;


public class ChatRowTrack extends ChatRow {

    ImageView mImageView;
    TextView mTextViewDes;
    TextView mTextViewprice;
    TextView mChatTextView;


    public ChatRowTrack(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        if (MessageHelper.getVisitorTrack(message) != null) {
            inflater.inflate(message.direct() == Message.Direct.RECEIVE ? R.layout.hd_row_received_message : R.layout.em_row_sent_track, this);
        }

    }

    @Override
    protected void onFindViewById() {
        mTextViewDes = (TextView) findViewById(R.id.tv_description);
        mTextViewprice = (TextView) findViewById(R.id.tv_price);
        mImageView = (ImageView) findViewById(R.id.iv_picture);
        mChatTextView = (TextView) findViewById(R.id.tv_chatcontent);
    }

    @Override
    protected void onUpdateView() {
    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        if (message.direct() == Message.Direct.RECEIVE) {
            //设置内容
            mChatTextView.setText(txtBody.getMessage());
            //设置长按事件监听
            mChatTextView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    activity.startActivityForResult(new Intent(activity, ContextMenuActivity.class)
                            .putExtra("position", position)
                            .putExtra("type", Message.Type.TXT.ordinal()), CustomChatFragment.REQUEST_CODE_CONTEXT_MENU);
                    return true;
                }
            });
            return;
        }
        VisitorTrack visitorTrack = MessageHelper.getVisitorTrack(message);
        if (visitorTrack == null) {
            return;
        }
        mTextViewDes.setText(visitorTrack.getDesc());
        mTextViewprice.setText(visitorTrack.getPrice());
        String imageUrl = visitorTrack.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(com.hyphenate.helpdesk.R.drawable.hd_default_image).into(mImageView);
        }
    }

    @Override
    protected void onBubbleClick() {

    }
}
