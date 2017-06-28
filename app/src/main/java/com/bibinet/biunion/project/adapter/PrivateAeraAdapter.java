package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bibinet.biunion.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-27.
 */

public class PrivateAeraAdapter extends RecyclerView.Adapter<PrivateAeraAdapter.PrivateViewHolder> {

    private Context context;
    private AreaOnclickListioner areaOnclickListioner;
    private String[] area = new String[]{"北京市", "天津市", "河北省", "山西省", "内蒙古", "辽宁省", "吉林省", "黑龙江省", "上海市", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省"
            , "河南省", "湖北省", "湖南省", "广东省", "广西", "重庆市", "四川省", "贵州省", "云南省", "西藏", "陕西省", "甘肃省", "青海省", "宁夏", "新疆",
            "台湾省", "香港", "澳门"};

    public PrivateAeraAdapter(Context context) {
        this.context = context;
    }
    public void setOnAreaClickLitioner(AreaOnclickListioner areaClickLitioner){
        this.areaOnclickListioner=areaClickLitioner;
    }
    @Override
    public PrivateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_area, parent, false);
        PrivateViewHolder privateViewHolder = new PrivateViewHolder(view);
        return privateViewHolder;
    }
    private int clickPos = -1;
    @Override
    public void onBindViewHolder(PrivateViewHolder holder, final int position) {
    holder.areaTextview.setText(area[position]);
        if(clickPos == position){
            holder.areaTextview.setSelected(true);
        }else{
            holder.areaTextview.setSelected(false);
        }
        holder.areaTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaOnclickListioner.onAearClickListioner(v);
                clickPos = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return area.length;
    }
   public interface AreaOnclickListioner{void onAearClickListioner(View view);
    }

    class PrivateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.areaTextview)
        TextView areaTextview;
        public PrivateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
