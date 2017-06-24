package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.utils.TimeUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

/**
 * Created by 吴昊 on 2017-6-16.
 */

public class ChatAdapter extends BaseAdapter {

    @Override
    public final int getViewTypeCount() {
        return getItemViewTypeId().length;
    }

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
        int currentType = getItemViewType(i);
        for(int typeI=0 ; typeI<getItemViewTypeId().length ; typeI++){
            //类型一致时执行 选一执行
            if(currentType==getItemViewTypeId()[typeI]){
                Object o;
                if(view==null){
                    view = LayoutInflater.from(getContext()).inflate(getLayoutId()[typeI], viewGroup, false);
                    o = initView(currentType, view);
                    view.setTag(o);
                }else{
                    o = view.getTag();
                }
                initValue(i, currentType, o);
                break;
            }
        }
        return view;
    }

    private List<EMMessage> dataList;
    private Context mContext;
    private ListView listView;

    private final int TYPE_MY = 0;
    private final int TYPE_YOUR = 1;

    private final int CURRENT = 5 * 60 * 1000;

    private int [] layout = {R.layout.adapter_chat_my, R.layout.adapter_chat_your};
    private int [] type = {TYPE_MY, TYPE_YOUR};
    public ChatAdapter(List<EMMessage> dataList, Context mContext, ListView listView) {
        this.dataList = dataList;
        this.mContext = mContext;
        this.listView = listView;
    }


    @Override
    public int getItemViewType(int position) {
        EMMessage msg = dataList.get(position);
        //得到发送对象 如果发送对象是自己 就是your 否则就都是我发的
        if(msg.getTo().equals("user")){
            return TYPE_YOUR;
        }
        return TYPE_MY;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    private Context getContext() {
        return mContext;
    }

    private void initValue(int position, int currentType, Object holder) {
        switch (currentType){
            case TYPE_MY:
                ViewHolderMy my = (ViewHolderMy) holder;
                EMMessage emmM = dataList.get(position);
                EMMessageBody bodyM = emmM.getBody();
                if(bodyM instanceof EMTextMessageBody){
                    EMTextMessageBody txtM = (EMTextMessageBody) bodyM;
                    my.txt.setText(txtM.getMessage());
                }
                String time = computeTime(position, dataList);
                if(time==null){
                    my.date.setVisibility(View.GONE);
                }else{
                    my.date.setVisibility(View.VISIBLE);
                    my.date.setText(time);
                }
                break;
            case TYPE_YOUR:
                ViewHolderYour your = (ViewHolderYour) holder;
                EMMessage emmY = dataList.get(position);
                EMMessageBody bodyY = emmY.getBody();
                if(bodyY instanceof EMTextMessageBody){
                    EMTextMessageBody txtM = (EMTextMessageBody) bodyY;
                    your.txt.setText(txtM.getMessage());
                }
                String timeY = computeTime(position, dataList);
                if(timeY==null){
                    your.date.setVisibility(View.GONE);
                }else{
                    your.date.setVisibility(View.VISIBLE);
                    your.date.setText(timeY);
                }
                break;
        }
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

    private Object initView(int currentType, View view) {
        Object o = null;
        switch (currentType){
            case TYPE_MY:
                o = new ViewHolderMy();
                ViewHolderMy my = (ViewHolderMy) o;
                my.txt = (TextView) view.findViewById(R.id.ada_chat_my_txt);
                my.date = (TextView) view.findViewById(R.id.ada_chat_my_date);
                break;
            case TYPE_YOUR:
                o = new ViewHolderYour();
                ViewHolderYour your = (ViewHolderYour) o;
                your.txt = (TextView) view.findViewById(R.id.ada_chat_your_txt);
                your.date = (TextView) view.findViewById(R.id.ada_chat_your_date);
                break;
        }
        return o;
    }


    private int[] getLayoutId() {
        return layout;
    }

    public int[] getItemViewTypeId() {
        return type;
    }

    public void addMessage() {
        notifyDataSetChanged();
        //选到最后一个
        listView.setSelection(dataList.size()-1);
    }

    private class ViewHolderMy{
        TextView txt, date;
    }
    private class ViewHolderYour{
        TextView txt, date;
    }
}
