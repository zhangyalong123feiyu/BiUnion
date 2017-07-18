package com.bibinet.biunion.project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.View;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.adapter.TicketListAdapter;
import com.bibinet.biunion.project.builder.IListener;
import com.bibinet.biunion.project.utils.ListenerManager;
import com.bibinet.biunion.project.utils.Preferences;
import com.google.gson.Gson;
import com.hyphenate.helpdesk.callback.ValueCallBack;
import com.hyphenate.helpdesk.domain.TicketEntity;
import com.hyphenate.helpdesk.domain.TicketListResponse;
import com.hyphenate.helpdesk.manager.TicketManager;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaveMessageActivity extends Activity implements View.OnClickListener,IListener,SwipeRefreshLayout.OnRefreshListener {
    private static final int MSG_LOAD_MORE_DATA = 0x01;
    private static final int MSG_REFRESH_DATA = 0x02;
    private static final int MSG_LOAD_ERROR = 0x03;
    private static final int PER_PAGE_COUNT = 15;
    private int mCurPageNo;
    private WeakHandler mWeakHandler;

    private List<TicketEntity> ticketEntityList = Collections.synchronizedList(new ArrayList<TicketEntity>());
    private EasyRecyclerView easyRecyclerView;
    private TicketListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leavemessage);
        mWeakHandler = new WeakHandler(this);
        mCurPageNo = 0;
        initView();
        loadFirstDatas();
        ListenerManager.getInstance().registerListener(this);

    }


    private void initView(){
        easyRecyclerView = (EasyRecyclerView)findViewById(R.id.recyclerview);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        easyRecyclerView.setLayoutManager(mLayoutManager);
        //设置分割线
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, this.getResources().getDisplayMetrics()));
        itemDecoration.setDrawLastItem(false);
        easyRecyclerView.addItemDecoration(itemDecoration);

        easyRecyclerView.setAdapterWithProgress(mAdapter = new TicketListAdapter(this));
        mAdapter.setMore(R.layout.em_view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //加载更多
                loadMoreData();
            }
        });
        mAdapter.setError(R.layout.em_view_error);
        mAdapter.setNoMore(R.layout.em_view_nomore);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TicketEntity ticketEntity = mAdapter.getItem(position);
                Intent intent = new Intent();
                intent.setClass(LeaveMessageActivity.this, TicketDetailActivity.class);
                intent.putExtra("ticket", ticketEntity);
                startActivity(intent);
            }
        });

    }



    @Override
    public void notifyEvent(String str, Object obj) {
        if (str.equals("clearTicketEvent")){
            refreshView(new ArrayList<TicketEntity>());
        }else {
            onRefresh();
        }
    }


    private static class WeakHandler extends Handler {
        WeakReference<LeaveMessageActivity> weakReference;
        public WeakHandler(LeaveMessageActivity customServiceActivity){
            this.weakReference = new WeakReference<LeaveMessageActivity>(customServiceActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LeaveMessageActivity customServiceActivity = weakReference.get();
            if (customServiceActivity != null){
                switch (msg.what){
                    case MSG_LOAD_MORE_DATA:
                        customServiceActivity.updateView((List<TicketEntity>) msg.obj);
                        break;
                    case MSG_REFRESH_DATA:
                        customServiceActivity.refreshView((List<TicketEntity>) msg.obj);
                        break;
                    case MSG_LOAD_ERROR:
                        customServiceActivity.loadDataError();
                        break;
                }


            }

        }
    }

    public void updateView(List<TicketEntity> entityList){
        if (entityList == null || entityList.size() == 0){
            easyRecyclerView.setRefreshing(false);
            mAdapter.stopMore();
            return;
        }
        ticketEntityList.addAll(entityList);
        mAdapter.addAll(entityList);
        mAdapter.notifyDataSetChanged();
        mAdapter.sort(new Comparator<TicketEntity>() {
            @Override
            public int compare(TicketEntity lhs, TicketEntity rhs) {
                return rhs.getUpdated_at().compareTo(lhs.getUpdated_at());
            }
        });
        mAdapter.pauseMore();
    }

    public void loadDataError(){
        easyRecyclerView.setRefreshing(false);
        easyRecyclerView.showError();
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }

    public void refreshView(List<TicketEntity> entityList){
        if (entityList == null){
            easyRecyclerView.setRefreshing(false);
            mAdapter.pauseMore();
            return;
        }
        ticketEntityList.clear();
        ticketEntityList.addAll(entityList);
        mAdapter.clear();
        mAdapter.addAll(ticketEntityList);
        mAdapter.notifyDataSetChanged();
        mAdapter.sort(new Comparator<TicketEntity>() {
            @Override
            public int compare(TicketEntity lhs, TicketEntity rhs) {
                return rhs.getUpdated_at().compareTo(lhs.getUpdated_at());
            }
        });
        if (entityList.size() < PER_PAGE_COUNT){
            mAdapter.stopMore();
        }
        mAdapter.pauseMore();
    }

    private void loadMoreData(){
        final int nextPage = mCurPageNo + 1;
        String target = Preferences.getInstance().getCustomerAccount();
        String tenantId = Preferences.getInstance().getTenantId();
        String projectId = Preferences.getInstance().getProjectId();

        TicketManager.getInstance().getTickets(projectId, target, nextPage, PER_PAGE_COUNT, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String value) {
                mCurPageNo = nextPage;
                Gson gson = new Gson();
                Message message = mWeakHandler.obtainMessage();
                TicketListResponse ticketListResponse = gson.fromJson(value, TicketListResponse.class);
                if (ticketListResponse != null){
                    message.obj = ticketListResponse.getEntities();
                }
                message.what = MSG_LOAD_MORE_DATA;
                mWeakHandler.sendMessage(message);
            }

            @Override
            public void onError(int error, String errorMsg) {
                mWeakHandler.sendEmptyMessage(MSG_LOAD_MORE_DATA);
            }
        });

    }


    private void loadFirstDatas() {
        String target = Preferences.getInstance().getCustomerAccount();
        String projectId = Preferences.getInstance().getProjectId();

        TicketManager.getInstance().getTickets(projectId, target, 0, PER_PAGE_COUNT, new ValueCallBack<String>() {
            @Override
            public void onSuccess(String value){
                Gson gson = new Gson();
                Message message = mWeakHandler.obtainMessage();
                TicketListResponse ticketListResponse = gson.fromJson(value,TicketListResponse.class);
                message.what = MSG_REFRESH_DATA;
                message.obj = ticketListResponse.getEntities();
                mCurPageNo = 0;
                mWeakHandler.sendMessage(message);

            }

            @Override
            public void onError(int error, String errorMsg) {
                mWeakHandler.sendEmptyMessage(MSG_LOAD_ERROR);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ListenerManager.getInstance().unRegisterListener(this);
    }

    @Override
    public void onRefresh() {
        loadFirstDatas();
    }


    //右上角聊天
    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }
}
