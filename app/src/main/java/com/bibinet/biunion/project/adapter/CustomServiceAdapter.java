package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.ui.activity.ChatActivity;
import com.bibinet.biunion.project.utils.TimeUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

/**
 * Created by 吴昊 on 2017-6-16.
 */

public class CustomServiceAdapter extends BaseAdapter {

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public final View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder t;
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(getLayoutId(), viewGroup, false);
            t = initView(view);
            view.setTag(t);
        }else{
            t = (ViewHolder) view.getTag();
        }
        initValue(i, t);
        return view;
    }

    private List<List<EMMessage>> dataList;
    private Context mContext;
    private ListView listView;

    private final int CURRENT = 5 * 60 * 1000;

    public CustomServiceAdapter(List<List<EMMessage>> dataList, Context mContext, ListView listView) {
        this.dataList = dataList;
        this.mContext = mContext;
        this.listView = listView;
    }

    private Context getContext() {
        return mContext;
    }

    private void initValue(int position, ViewHolder holder) {
        List<EMMessage> emmML = dataList.get(position);

        EMMessage emMessage = emmML.get(emmML.size()-1);
        if(emMessage.getUserName().equals("service")){
            holder.name.setText("客服小米");
        }
        String time = computeTime(emmML.size()-1 ,emmML);
        holder.date.setText(time);
        EMMessageBody body = emMessage.getBody();
        if(body instanceof EMTextMessageBody){
            EMTextMessageBody b = (EMTextMessageBody) body;
            holder.lastMessage.setText(b.getMessage());
        }
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ChatActivity.class));
            }
        });
        //得到未读消息数量 不是自己 是对方
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation("service");
        int count = conversation.getUnreadMsgCount();
        if(count>0){
            holder.isNewMessage.setVisibility(View.VISIBLE);
        }else{
            holder.isNewMessage.setVisibility(View.GONE);
        }
    }

    protected ViewHolder initView(View view) {
        ViewHolder o = new ViewHolder();
        ViewHolder holder = (ViewHolder) o;
        holder.name = (TextView) view.findViewById(R.id.ada_custom_service_name);
        holder.date = (TextView) view.findViewById(R.id.ada_custom_service_date);
        holder.lastMessage = (TextView) view.findViewById(R.id.ada_custom_service_last_message);
        holder.isNewMessage = view.findViewById(R.id.ada_custom_service_is_new_message);
        holder.main = view.findViewById(R.id.ada_custom_service);
        return o;
    }

    private int getLayoutId() {
        return R.layout.adapter_custom_service;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }


    class ViewHolder{
        TextView name, lastMessage, date;
        View isNewMessage, main;
    }

    private String computeTime(int position, List<EMMessage> dataList) {
        long time=0;
        long currentTime=0;
        if(position==0){
            time = dataList.get(position).getMsgTime();
            currentTime = System.currentTimeMillis();
        }else{
            currentTime = dataList.get(position).getMsgTime();
            time = dataList.get(position-1).getMsgTime();
        }
        String res = "";
        if(currentTime-time<CURRENT){
            res = null;
        }else{
            String currentYear = TimeUtils.getTimeFormat("yyyy", position==0?currentTime:time);
            String year = TimeUtils.getTimeFormat("yyyy", position==0?time:currentTime);
            if(!currentYear.equals(year)){
                res = TimeUtils.getTimeFormat("yyyy/MM/dd HH:mm", position==0?time:currentTime);
            }else{
                String currentDay = TimeUtils.getTimeFormat("dd", position==0?currentTime:time);
                String day = TimeUtils.getTimeFormat("dd", position==0?time:currentTime);
                if(!day.equals(currentDay)){
                    String toDay = TimeUtils.getTimeFormat("dd", System.currentTimeMillis());
                    if(toDay.equals(day)){
                        res = TimeUtils.getTimeFormat("HH:mm", position==0?time:currentTime);
                    }else{
                        res = TimeUtils.getTimeFormat("MM/dd HH:mm", position==0?time:currentTime);
                    }
                }else{
                    res = TimeUtils.getTimeFormat("HH:mm", position==0?time:currentTime);
                }
            }
        }
        return res;
    }

    public void addMessage() {
        notifyDataSetChanged();
        //选到最后一个
        listView.setSelection(dataList.size()-1);
    }
}
