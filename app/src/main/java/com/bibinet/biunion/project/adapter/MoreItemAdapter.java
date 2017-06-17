package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bibinet.biunion.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-6-15.
 */

public class MoreItemAdapter extends RecyclerView.Adapter<MoreItemAdapter.MoreViewHolder> {
    private List<String> datas = new ArrayList<>();
    private Context context;
    private ProjectTextClickListioner textClickListioner;
    public MoreItemAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public MoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_poptextview, parent, false);
        MoreViewHolder moreViewHolder = new MoreViewHolder(view);
        return moreViewHolder;
    }

    @Override
    public void onBindViewHolder(MoreViewHolder holder, int position) {
        holder.projectText.setText(datas.get(position));
        holder.projectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textClickListioner.onProjectTextClickLitioner(v);
            }
        });
    }
    public void setOnclickListioner(ProjectTextClickListioner projectTextClickListioner){
        this.textClickListioner=projectTextClickListioner;
    }
    @Override
    public int getItemCount() {
        return datas.size()!=0?datas.size():0;
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.projectText)
        TextView projectText;
        public MoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
   public interface ProjectTextClickListioner{
      void onProjectTextClickLitioner(View view);
    }
}
