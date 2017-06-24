package com.bibinet.biunion.project.ui.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.adapter.ChatAdapter;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ChatActivity extends Activity implements View.OnClickListener, EMMessageListener {
    private EditText editText;
    private EMMessageListener msgListener;

    private ChatAdapter chatAdapter;

    private ListView listView;

    private TextView name;
    private EMChatManager emChatManager;
    private EMGroupManager emGroupManager;

    private Handler handlerConnectState = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int res = msg.what;
            //登录失败
            if (res == 0) {
                //退出该界面
                finish();
            } else if (res == 1) {
                Map<String, EMConversation> conversations = emChatManager.getAllConversations();
                Iterator<String> it = conversations.keySet().iterator();
                while (it.hasNext()){
                    String key = it.next();
                    EMConversation em = conversations.get(key);
                    List<EMMessage> l = em.getAllMessages();

                    dataList.addAll(0, l);
                    listView.setSelection(dataList.size()-1);
                    chatAdapter.notifyDataSetChanged();
                }
                emChatManager.markAllConversationsAsRead();
            }
        }
    };

    @Override
    public void onClick(View view) {
        String msg = editText.getText().toString();
        //发送的消息不能为空
        if(msg.equals("")){
            return;
        }
        //点击给service（客服发消息） 目前只有一个客服 理论上是随机数分配
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        String toChatUsername = "service";
        EMMessage message = EMMessage.createTxtSendMessage(msg, toChatUsername);

        emChatManager.sendMessage(message);

        dataList.add(message);
        chatAdapter.addMessage();
        editText.setText("");
    }
    private List<EMMessage> dataList;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        emChatManager.removeMessageListener(this);
        //存入数据库
//        EMClient.getInstance().chatManager().importMessages(dataList);
    }

    private Handler handlerMessageListener = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            chatAdapter.addMessage();
        }
    };

    @Override
    public void onMessageReceived(List<EMMessage> list) {
        dataList.add(list.get(0));
        handlerMessageListener.sendEmptyMessage(0);
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editText = (EditText) findViewById(R.id.act_chat_input);
        listView = (ListView) findViewById(R.id.act_chat_main);
        name = (TextView) findViewById(R.id.act_chat_name);

        name.setText("客服小米");

        findViewById(R.id.act_chat_send).setOnClickListener(this);

        dataList = new ArrayList<EMMessage>();
        chatAdapter = new ChatAdapter(dataList, this, listView);
        listView.setAdapter(chatAdapter);

        emChatManager = EMClient.getInstance().chatManager();
        emGroupManager = EMClient.getInstance().groupManager();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //聊天需要登录
                //注册失败会抛出HyphenateException
//                try {
//                    //注册
//                    EMClient.getInstance().createAccount("110nihaoma", "123456");//同步方法
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
//                    Log.e("失败", "失败");
//                }
                EMClient.getInstance().login("user", "123456", new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        emGroupManager.loadAllGroups();
                        emChatManager.loadAllConversations();
                        Log.e("main", "登录聊天服务器成功！");
                        //handler通知结果
                        Message msg = Message.obtain();
                        msg.what = 1;
                        handlerConnectState.sendMessage(msg);
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        Log.d("main", "登录聊天服务器失败！");
                        //handler通知结果
                        Message msg = Message.obtain();
                        msg.what = 0;
                        handlerConnectState.sendMessage(msg);
                    }
                });
            }
        }).start();

        emChatManager.addMessageListener(this);

        addOnSoftKeyBoardVisibleListener(this, new IKeyBoardVisibleListener() {
            @Override
            public void onSoftKeyBoardVisible(boolean visible, int windowBottom) {
                handler.sendEmptyMessage(0);
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            listView.setSelection(dataList.size()-1);
        }
    };

    interface IKeyBoardVisibleListener{
        void onSoftKeyBoardVisible(boolean visible, int windowBottom);
    }
    boolean isVisiableForLast = false;

    public void addOnSoftKeyBoardVisibleListener(Activity activity, final IKeyBoardVisibleListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                //计算出可见屏幕的高度
                int displayHight = rect.bottom - rect.top;
                //获得屏幕整体的高度
                int hight = decorView.getHeight();
                //获得键盘高度
                int keyboardHeight = hight-displayHight;
                boolean visible = (double) displayHight / hight < 0.8;
                if(visible != isVisiableForLast){
                    listener.onSoftKeyBoardVisible(visible,keyboardHeight );
                }
                isVisiableForLast = visible;
            }
        });
    }
}
