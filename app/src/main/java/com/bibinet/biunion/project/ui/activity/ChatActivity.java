package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.ui.fragment.CustomChatFragment;
import com.bibinet.biunion.project.utils.Constant;
import com.bibinet.biunion.project.utils.MessageHelper;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.recorder.MediaManager;
import com.hyphenate.helpdesk.easeui.ui.BaseActivity;
import com.hyphenate.helpdesk.easeui.ui.ChatFragment;
import com.hyphenate.helpdesk.easeui.util.CommonUtils;
import com.hyphenate.helpdesk.easeui.util.Config;

public class ChatActivity extends BaseActivity {

    public static ChatActivity instance = null;

    private ChatFragment chatFragment;

    String toChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.hd_activity_chat);
        instance = this;
//        toChatUsername = getIntent().getExtras().getString(Config.EXTRA_SERVICE_IM_NUMBER);
            toChatUsername="张亚龙";
        //可以直接new ChatFragment使用
        chatFragment = new CustomChatFragment();
        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
        sendOrderOrTrack();
    }


    /**
     * 发送订单或轨迹消息
     */
    private void sendOrderOrTrack() {
        Bundle bundle = getIntent().getBundleExtra(Config.EXTRA_BUNDLE);
        if (bundle != null) {
            //检查是否是从某个商品详情进来
            int selectedIndex = bundle.getInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, Constant.INTENT_CODE_IMG_SELECTED_DEFAULT);
            switch (selectedIndex) {
                case Constant.INTENT_CODE_IMG_SELECTED_1:
                case Constant.INTENT_CODE_IMG_SELECTED_2:
                    sendOrderMessage(selectedIndex);
                    break;
                case Constant.INTENT_CODE_IMG_SELECTED_3:
                case Constant.INTENT_CODE_IMG_SELECTED_4:
                    sendTrackMessage(selectedIndex);
                    break;
            }
        }
    }

    /**
     * 发送订单消息
     *
     * 不发送则是saveMessage
     * @param selectedIndex
     */
    private void sendOrderMessage(int selectedIndex){
        Message message = Message.createTxtSendMessage(getMessageContent(selectedIndex), toChatUsername);
        message.addContent(MessageHelper.createOrderInfo(this, selectedIndex));
        ChatClient.getInstance().chatManager().saveMessage(message);
    }

    /**
     * 发送轨迹消息
     * @param selectedIndex
     */
    private void sendTrackMessage(int selectedIndex) {
        Message message = Message.createTxtSendMessage(getMessageContent(selectedIndex), toChatUsername);
        message.addContent(MessageHelper.createVisitorTrack(this, selectedIndex));
        ChatClient.getInstance().chatManager().sendMessage(message);
    }

    private String getMessageContent(int selectedIndex){
        switch (selectedIndex){
            case 1:
                return getResources().getString(R.string.em_example1_text);
            case 2:
                return getResources().getString(R.string.em_example2_text);
            case 3:
                return getResources().getString(R.string.em_example3_text);
            case 4:
                return getResources().getString(R.string.em_example4_text);
        }
        // 内容自己随意定义。
        return "";
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
        instance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra(Config.EXTRA_SERVICE_IM_NUMBER);
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (CommonUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
