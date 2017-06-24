package com.bibinet.biunion.project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.adapter.CustomServiceAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomServiceActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_service);

        //环信初始化配置
        //初始化环信
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        mainLV = (ListView) findViewById(R.id.act_custom_service_main);
        notMessageV = findViewById(R.id.act_custom_service_not_message);

        findViewById(R.id.act_custom_service_chat).setOnClickListener(this);
    }

    private ListView mainLV;

    private List<List<EMMessage>> dataList;

    private View notMessageV;

    @Override
    protected void onResume() {
        super.onResume();
        dataList = new ArrayList<List<EMMessage>>();

        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        Iterator<String> it = conversations.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            EMConversation em = conversations.get(key);
            List<EMMessage> l = em.getAllMessages();
            for(int i=0 ; i<l.size() ; i++){
//                LogUtils.e("-,", l.get(i).getBody().toString()+"--"+key);
            }
            dataList.add(l);
        }
        mainLV.setAdapter(new CustomServiceAdapter(dataList, this, mainLV));
        if(dataList.size()<=0){
            notMessageV.setVisibility(View.VISIBLE);
        }else{
            notMessageV.setVisibility(View.GONE);
        }
    }

    //右上角聊天
    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }
}
