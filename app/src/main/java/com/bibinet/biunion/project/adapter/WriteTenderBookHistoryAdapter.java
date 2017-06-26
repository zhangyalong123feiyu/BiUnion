package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.bean.WriteTenderBookHistoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-6-26.
 */

public class WriteTenderBookHistoryAdapter extends RecyclerView.Adapter<WriteTenderBookHistoryAdapter.WriteTenderBookHistoryViewHolder> {
    private List<WriteTenderBookHistoryBean.ItemBean> historyInfo;
    private Context context;

    public WriteTenderBookHistoryAdapter(List<WriteTenderBookHistoryBean.ItemBean> historyInfo, Context context) {
        this.historyInfo = historyInfo;
        this.context = context;
    }

    @Override
    public WriteTenderBookHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_historywritebook, parent, false);
        WriteTenderBookHistoryViewHolder writeTenderBookHistoryViewHolder = new WriteTenderBookHistoryViewHolder(view);
        return writeTenderBookHistoryViewHolder;
    }

    @Override
    public void onBindViewHolder(WriteTenderBookHistoryViewHolder holder, int position) {
        holder.historyTenderBook.setText(String.valueOf(historyInfo.get(position).getTenderSelection()));
        holder.historyTenderType.setText(String.valueOf(historyInfo.get(position).getProjectType()));
        holder.tenderType.setText(String.valueOf(historyInfo.get(position).getTenderMode()));
        holder.tenderBookType.setText(String.valueOf(historyInfo.get(position).getTenderType()));
        holder.contactPerson.setText(String.valueOf(historyInfo.get(position).getContact()));
        holder.contactType.setText(String.valueOf(historyInfo.get(position).getCellPhone()));
        holder.contactEmail.setText(String.valueOf(historyInfo.get(position).getEmail()));
    }

    @Override
    public int getItemCount() {
        return historyInfo.size() == 0 ? 0 : historyInfo.size();
    }

    class WriteTenderBookHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView historyTenderBook;
        private TextView historyTenderType;
        private TextView tenderType;
        private TextView tenderBookType;
        private TextView contactPerson;
        private TextView contactType;
        private TextView contactEmail;

        public WriteTenderBookHistoryViewHolder(View itemView) {
            super(itemView);
            historyTenderBook=(TextView)itemView.findViewById(R.id.historyTenderBook);
            historyTenderType=(TextView)itemView.findViewById(R.id.historyTenderType);
            tenderType=(TextView)itemView.findViewById(R.id.tenderType);
            tenderBookType=(TextView)itemView.findViewById(R.id.tenderBookType);
            contactPerson=(TextView)itemView.findViewById(R.id.contactPerson);
            contactType=(TextView)itemView.findViewById(R.id.contactType);
            contactEmail=(TextView)itemView.findViewById(R.id.contactEmail);
        }
    }
}
