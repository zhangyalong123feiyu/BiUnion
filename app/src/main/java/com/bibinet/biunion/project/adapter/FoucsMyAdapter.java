package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.bean.FoucsedBean;
import com.bibinet.biunion.project.ui.activity.H5Activity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-6-23.
 */

public class FoucsMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private final LayoutInflater inflater;
    private Context context;
    private List<FoucsedBean.ItemBean> socailInfos;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有数据时
    public static final int LOAD_NODATA = 2;

    public static int Lastposition;

    public FoucsMyAdapter(Context context, List<FoucsedBean.ItemBean> socailInfos) {
        this.context = context;
        this.socailInfos = socailInfos;
        Lastposition = socailInfos.size();
        inflater = LayoutInflater.from(context);
        Log.i("TAG",socailInfos.size()+"-------foucs---------");
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断显示类型，来创建返回不同的View
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_projectinfo, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            FoucsItemHolder itemViewHolder = new FoucsItemHolder(view);
            return itemViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = inflater.inflate(R.layout.progressbar_item, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            FoucsMyAdapter.ProgressViewHolder footViewHolder = new FoucsMyAdapter.ProgressViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof FoucsItemHolder) {
            Log.i("TAG",socailInfos.size()+"-------foucscompanyname---------"+socailInfos.get(position).getProjectName());
            ((FoucsItemHolder) holder).companyName.setText(socailInfos.get(position).getProjectName());
            ((FoucsItemHolder) holder).projectDescrp.setText(socailInfos.get(position).getProjectDescrp());
            ((FoucsItemHolder) holder).projectLoaction.setText(socailInfos.get(position).getProjectLocation());
            ((FoucsItemHolder) holder).projectTime.setText(socailInfos.get(position).getProjectTime());

            if (socailInfos.get(position).getProjectType().equals("A")) {
                ((FoucsItemHolder) holder).projectTypeImage.setImageResource(R.mipmap.shouye_gongcheng);
            } else if (socailInfos.get(position).getProjectType().equals("B")) {
                ((FoucsItemHolder) holder).projectTypeImage.setImageResource(R.mipmap.shouye_huowu);
            } else {
                ((FoucsItemHolder) holder).projectTypeImage.setImageResource(R.mipmap.shouye_fuw);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, H5Activity.class);
                    intent.putExtra("detailUrl", socailInfos.get(position).getProjectUrl());
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof SearchActivityAdapter.ProgressViewHolder) {
            SearchActivityAdapter.ProgressViewHolder progressHolder = (SearchActivityAdapter.ProgressViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    progressHolder.textshow.setText("上拉加载更多...");
                    progressHolder.progressBar.setVisibility(View.INVISIBLE);
                    break;
                case LOADING_MORE:
                    progressHolder.textshow.setText("正在加载...");
                    progressHolder.progressBar.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NODATA:
                    progressHolder.textshow.setVisibility(View.GONE);
                    progressHolder.progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar loadMore;
        private TextView textshow;

        public FootViewHolder(View itemView) {
            super(itemView);
            loadMore = (ProgressBar) itemView.findViewById(R.id.loadMore);
            textshow = (TextView) itemView.findViewById(R.id.textshow);
        }
    }

    public void addMoreItem(List<FoucsedBean.ItemBean> newDatas) {
        System.out.print(this.socailInfos.size() + "原有数据");
        System.out.print(newDatas.size() + "新增有数据");
        this.socailInfos.addAll(newDatas);
        Lastposition = this.socailInfos.size();
        System.out.print(this.socailInfos.size() + "现有");
        notifyDataSetChanged();
    }

    /**
     * //上拉加载更多
     * PULLUP_LOAD_MORE=0;
     * //正在加载中
     * LOADING_MORE=1;
     * //加载完成已经没有更多数据了
     * NO_MORE_DATA=2;
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return socailInfos.size() > 0 ? socailInfos.size() + 1 : 1;
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public TextView textshow;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loadMore);
            textshow = (TextView) itemView.findViewById(R.id.textshow);
        }
    }
}

class FoucsItemHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.projectTypeImage)
    ImageView projectTypeImage;
    @BindView(R.id.companyName)
    TextView companyName;
    @BindView(R.id.projectDescrp)
    TextView projectDescrp;
    @BindView(R.id.projectLoaction)
    TextView projectLoaction;
    @BindView(R.id.projectTime)
    TextView projectTime;
    public FoucsItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
