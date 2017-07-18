package com.bibinet.biunion.project.chatrow;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.ui.activity.ForwardWebViewActivity;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.Message;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.hyphenate.helpdesk.easeui.adapter.MessageAdapter;
import com.hyphenate.helpdesk.easeui.util.SmileUtils;
import com.hyphenate.helpdesk.easeui.widget.chatrow.ChatRow;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by liyuzhao on 15/12/2016.
 */

public class ChatRowForm extends ChatRow {

    private TextView contentView;
    private String targetUrl;

    public ChatRowForm(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(message.direct() == Message.Direct.RECEIVE ? R.layout.em_row_received_form : R.layout.hd_row_sent_message, this);
    }

    @Override
    protected void onFindViewById() {
        contentView = (TextView) findViewById(R.id.tv_chatcontent);
    }

    @Override
    protected void onUpdateView() {
        if (adapter instanceof MessageAdapter) {
            ((MessageAdapter) adapter).refresh();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();

        Spannable span = SmileUtils.getSmiledText(context, txtBody.getMessage());
        // 设置内容
//        contentView.setText(span, TextView.BufferType.SPANNABLE);
        if (message.direct() == Message.Direct.RECEIVE){
            try {
                JSONObject jsonMsgType = message.getJSONObjectAttribute("msgtype");
                JSONObject jsonHtml = jsonMsgType.getJSONObject("html");
                targetUrl = jsonHtml.getString("url");
                String desc = jsonHtml.getString("desc");
                contentView.setText(desc);
            } catch (HyphenateException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        handleTextMessage();

    }


    protected void handleTextMessage() {
        boolean isShowProgress = UIProvider.getInstance().isShowProgress();
        if (message.direct() == Message.Direct.SEND) {
            setMessageSendCallback();
            switch (message.getStatus()) {
                case CREATE:
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.VISIBLE);
                    // 发送消息
                    break;
                case SUCCESS: // 发送成功
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.GONE);
                    break;
                case FAIL: // 发送失败
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.VISIBLE);
                    break;
                case INPROGRESS: // 发送中
                    if (isShowProgress)
                        progressBar.setVisibility(View.VISIBLE);
                    statusView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    protected void onBubbleClick() {
//        targetUrl = "http://172.17.3.98:8080/h5.html";
        if (TextUtils.isEmpty(targetUrl)){
            return;
        }
        Intent intent = new Intent();
        intent.setClass(context, ForwardWebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", targetUrl);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.em_activity_open, 0);
    }
}
