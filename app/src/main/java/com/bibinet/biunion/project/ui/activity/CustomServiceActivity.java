package com.bibinet.biunion.project.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.adapter.TicketListAdapter;
import com.bibinet.biunion.project.builder.IListener;
import com.bibinet.biunion.project.utils.Constant;
import com.bibinet.biunion.project.utils.GlideCircleTransform;
import com.bibinet.biunion.project.utils.ListenerManager;
import com.bibinet.biunion.project.utils.MessageHelper;
import com.bibinet.biunion.project.utils.Preferences;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.Conversation;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.OfficialAccount;
import com.hyphenate.helpdesk.callback.ValueCallBack;
import com.hyphenate.helpdesk.domain.TicketEntity;
import com.hyphenate.helpdesk.domain.TicketListResponse;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.easeui.util.SmileUtils;
import com.hyphenate.helpdesk.manager.TicketManager;
import com.hyphenate.util.DateUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import static com.bibinet.biunion.R.string.conversation;

public class CustomServiceActivity extends Activity implements View.OnClickListener {

    private ConversationAdapter adapter;
    private final List<Conversation> conversationList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private boolean progressShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_service);
        ListView mListView = (ListView)findViewById(R.id.listview);

        mListView.setAdapter(adapter = new ConversationAdapter(this, 1, conversationList));
        loadConversationList();
        adapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Conversation conversation = (Conversation) parent.getItemAtPosition(position);
                // 进入主页面
                Intent intent = new IntentBuilder(CustomServiceActivity.this)
                        .setTargetClass(ChatActivity.class)
                        .setVisitorInfo(MessageHelper.createVisitorInfo())
                        .setServiceIMNumber(conversation.conversationId())
                        .setTitleName(conversation.getOfficialAccount().getName())
                        .setShowUserNick(true)
                        .build();
                startActivity(intent);

            }
        });
    }

    class ConversationAdapter extends ArrayAdapter<Conversation> {

        ConversationAdapter(Context context, int resource, List<Conversation> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.em_row_conversation, null);
                viewHolder = new ViewHolder();
                viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.tvMessage = (TextView) convertView.findViewById(R.id.tv_message);
                viewHolder.tvUnreadCount = (TextView) convertView.findViewById(R.id.tv_unread);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Conversation conversation = getItem(position);
            if (conversation == null){
                return convertView;
            }
            if (conversation.getUnreadMsgCount() > 0){
                viewHolder.tvUnreadCount.setVisibility(View.VISIBLE);
                viewHolder.tvUnreadCount.setText(String.valueOf(conversation.getUnreadMsgCount()));
            }else{
                viewHolder.tvUnreadCount.setVisibility(View.GONE);
            }
            com.hyphenate.chat.Message lastMessage = conversation.getLastMessage();

            if (lastMessage != null){
                if (lastMessage.getType() == com.hyphenate.chat.Message.Type.TXT){
                    EMTextMessageBody body = (EMTextMessageBody)lastMessage.getBody();
                    viewHolder.tvMessage.setText(SmileUtils.getSmiledText(getContext(), body.getMessage()));
                }else if (lastMessage.getType() == com.hyphenate.chat.Message.Type.IMAGE){
                    viewHolder.tvMessage.setText("[图片]");
                }else if (lastMessage.getType() == com.hyphenate.chat.Message.Type.VOICE){
                    viewHolder.tvMessage.setText("[语音]");
                }else if (lastMessage.getType() == com.hyphenate.chat.Message.Type.VIDEO){
                    viewHolder.tvMessage.setText("[视频]");
                }else if (lastMessage.getType() == com.hyphenate.chat.Message.Type.LOCATION){
                    viewHolder.tvMessage.setText("[位置]");
                }else if (lastMessage.getType() == com.hyphenate.chat.Message.Type.FILE){
                    viewHolder.tvMessage.setText("[文件]");
                }
                viewHolder.tvTime.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));

            }else{
                viewHolder.tvMessage.setText("");
            }
            OfficialAccount officialAccount = conversation.getOfficialAccount();
            if (officialAccount == null){
                return convertView;
            }
            viewHolder.tvName.setText(officialAccount.getName());
            String imgUrl = officialAccount.getImg();
            if (imgUrl != null && imgUrl.startsWith("//")){
                imgUrl = "http:" + imgUrl;
            }
            Glide.with(getContext()).load(imgUrl).error(R.drawable.hd_default_avatar).transform(new GlideCircleTransform(getContext())).into(viewHolder.ivAvatar);
            return convertView;
        }

        class ViewHolder{
            ImageView ivAvatar;
            TextView tvName;
            TextView tvTime;
            TextView tvMessage;
            TextView tvUnreadCount;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh(){
        loadConversationList();
        adapter.notifyDataSetChanged();

    }

    private void loadConversationList(){
        Hashtable<String, Conversation> allConversations =
                ChatClient.getInstance().chatManager().getAllConversations();
        synchronized (conversationList){
            conversationList.clear();
            for (String conversationId : allConversations.keySet()){
                Conversation item = allConversations.get(conversationId);
                if (item.getOfficialAccount() != null){
                    conversationList.add(item);
                }
            }
        }



    }



    //右上角聊天
    @Override
    public void onClick(View view) {
//       startActivity(new Intent(this, LoginActivityHuanX.class).putExtra(Constant.MESSAGE_TO_INTENT_EXTRA,
//                Constant.MESSAGE_TO_PRE_SALES));
//        startActivity(new Intent(this, ChatActivity.class));

//        Intent intent = getIntent();
//        selectedIndex = intent.getIntExtra(Constant.INTENT_CODE_IMG_SELECTED_KEY,
//                Constant.INTENT_CODE_IMG_SELECTED_DEFAULT);
//        messageToIndex = intent.getIntExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);

        //ChatClient.getInstance().isLoggedInBefore() 可以检测是否已经登录过环信，如果登录过则环信SDK会自动登录，不需要再次调用登录操作
        if (ChatClient.getInstance().isLoggedInBefore()) {
            progressDialog = getProgressDialog();
            progressDialog.setMessage(getResources().getString(R.string.is_contact_customer));
            progressDialog.show();
            toChatActivity();
        } else {
            //随机创建一个用户并登录环信服务器
            Toast.makeText(CustomServiceActivity.this,"环信登录失败",Toast.LENGTH_SHORT).show();
            return;
        }

    }
    private ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(CustomServiceActivity.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progressShow = false;
                }
            });
        }
        return progressDialog;
    }
    private void toChatActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!CustomServiceActivity.this.isFinishing())
                    progressDialog.dismiss();

                //此处演示设置技能组,如果后台设置的技能组名称为[shouqian|shouhou],这样指定即分配到技能组中.
                //为null则不按照技能组分配,同理可以设置直接指定客服scheduleAgent
                String queueName = "张亚龙";
//                switch (messageToIndex){
//                    case Constant.MESSAGE_TO_AFTER_SALES:
//                        queueName = "shouhou";
//                        break;
//                    case Constant.MESSAGE_TO_PRE_SALES:
//                        queueName = "shouqian";
//                        break;
//                }
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, Constant.INTENT_CODE_IMG_SELECTED_DEFAULT);
                //设置点击通知栏跳转事件
                Conversation conversation = ChatClient.getInstance().chatManager().getConversation(Preferences.getInstance().getCustomerAccount());
                String titleName = null;
                if (conversation.getOfficialAccount() != null){
                    titleName = conversation.getOfficialAccount().getName();
                }
                // 进入主页面
                Intent intent = new IntentBuilder(CustomServiceActivity.this)
                        .setTargetClass(ChatActivity.class)
                        .setVisitorInfo(MessageHelper.createVisitorInfo())
                        .setServiceIMNumber(Preferences.getInstance().getCustomerAccount())
                        .setScheduleQueue(MessageHelper.createQueueIdentity(queueName))
                        .setTitleName(titleName)
                        .setShowUserNick(true)
                        .setBundle(bundle)
                        .build();
                startActivity(intent);
                finish();

            }
        });
    }
}
