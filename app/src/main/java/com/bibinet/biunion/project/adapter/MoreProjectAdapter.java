package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.ui.activity.H5Activity;
import com.bibinet.biunion.project.ui.activity.PlatFormActivity;
import com.bibinet.biunion.project.utils.BannerUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-1-6.
 */
public class MoreProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflater;
    private Context context;
    private List<ProjectInfoBean.ItemsBean> socailInfos;
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    public static final int LOAD_NODATA = 2;
    public static int Lastposition;

    public MoreProjectAdapter(Context context, List<ProjectInfoBean.ItemsBean> socailInfos) {
        this.context = context;
        this.socailInfos = socailInfos;
        Lastposition = socailInfos.size();
        inflater = LayoutInflater.from(context);
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
            View view = inflater.inflate(R.layout.item_projectdetail, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            ItemHolder itemViewHolder = new ItemHolder(view);
            return itemViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = inflater.inflate(R.layout.progressbar_item, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            ProgressViewHolder footViewHolder = new ProgressViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).projcetName.setText(socailInfos.get(position).getProjectName());
            ((ItemHolder) holder).projectTitle.setText(socailInfos.get(position).getProjectTitle());
            ((ItemHolder) holder).projcetDescrp.setText(socailInfos.get(position).getProjectDescrp());
            ((ItemHolder) holder).projectLocation.setText(socailInfos.get(position).getProjectLocation());
            ((ItemHolder) holder).projcetOffer.setText(socailInfos.get(position).getProjectAmount());
            ((ItemHolder) holder).publishTime.setText(socailInfos.get(position).getProjectPublishTime());
            ((ItemHolder) holder).projcetOffer.setText(socailInfos.get(position).getProjectAmount());

            if (socailInfos.get(position).getProjectType().equals("A")) {
                ((ItemHolder) holder).projectImage.setImageResource(R.mipmap.shouye_gongcheng);
            } else if (socailInfos.get(position).getProjectType().equals("B")) {
                ((ItemHolder) holder).projectImage.setImageResource(R.mipmap.shouye_huowu);
            } else {
                ((ItemHolder) holder).projectImage.setImageResource(R.mipmap.shouye_fuw);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, H5Activity.class);
                    intent.putExtra("detailUrl", socailInfos.get(position).getProjectUrl());
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof ProgressViewHolder) {
            ProgressViewHolder progressHolder = (ProgressViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    progressHolder.textshow.setVisibility(View.GONE);
                    progressHolder.progressBar.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    progressHolder.textshow.setVisibility(View.GONE);
                    progressHolder.progressBar.setVisibility(View.GONE);
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

    public void addMoreItem(List<ProjectInfoBean.ItemsBean> newDatas) {
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

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.projectTitle)
        TextView projectTitle;
        @BindView(R.id.projcetName)
        TextView projcetName;
        @BindView(R.id.projcetOffer)
        TextView projcetOffer;
        @BindView(R.id.projcetDescrp)
        TextView projcetDescrp;
        @BindView(R.id.projectImage)
        ImageView projectImage;
        @BindView(R.id.projectType)
        TextView projectType;
        @BindView(R.id.projectLocation)
        TextView projectLocation;
        @BindView(R.id.publishTime)
        TextView publishTime;
        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
