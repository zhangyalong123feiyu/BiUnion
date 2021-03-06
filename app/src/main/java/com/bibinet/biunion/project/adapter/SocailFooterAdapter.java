package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.bean.BannerBean;
import com.bibinet.biunion.project.bean.ProjectInfoBean;
import com.bibinet.biunion.project.builder.MyViewPager;
import com.bibinet.biunion.project.ui.activity.H5Activity;
import com.bibinet.biunion.project.ui.activity.PlatFormActivity;
import com.bibinet.biunion.project.utils.BannerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-1-6.
 */
public class SocailFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflater;

    private Context context;
    private List<ProjectInfoBean.ItemsBean> socailInfos;
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
    public static final int LOAD_NODATA=2;

    public static int Lastposition;
    private BannerUtils bannerUtils;
    private List<BannerBean.ItemBean> bannerInfo=new ArrayList<>();
    private int selectType;
    private int detailType;
    public SocailFooterAdapter(Context context, List<ProjectInfoBean.ItemsBean> socailInfos,List<BannerBean.ItemBean> bannerUrl,int selectType,int detailType) {
        this.context = context;
        this.socailInfos = socailInfos;
        this.bannerInfo=bannerUrl;
        Lastposition = socailInfos.size();
        inflater = LayoutInflater.from(context);
        this.selectType=selectType;
        this.detailType=detailType;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setBannerUrl(List<BannerBean.ItemBean> bannerInfos){
        this.bannerInfo=bannerInfos;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断显示类型，来创建返回不同的View
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_projectinfo, parent, false);
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
        } else if (viewType == TYPE_HEADER) {
            View headerView = inflater.inflate(R.layout.item_banner, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(headerView);
            return headerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).companyName.setText(socailInfos.get(position-1).getProjectName());
            ((ItemHolder) holder).projectDescrp.setText(socailInfos.get(position-1).getProjectDescrp());
            ((ItemHolder) holder).projectLoaction.setText(socailInfos.get(position-1).getProjectLocation());
            ((ItemHolder) holder).projectTime.setText(socailInfos.get(position-1).getProjectTime());
            if (socailInfos.get(position-1).getProjectType().equals("A")) {
                ((ItemHolder) holder).projectTypeImage.setImageResource(R.mipmap.shouye_gongcheng);
            } else if (socailInfos.get(position-1).getProjectType().equals("B")) {
                ((ItemHolder) holder).projectTypeImage.setImageResource(R.mipmap.shouye_huowu);
            } else {
                ((ItemHolder) holder).projectTypeImage.setImageResource(R.mipmap.shouye_fuw);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, H5Activity.class);
                    intent.putExtra("detailUrl", socailInfos.get(position).getProjectUrl());
                    intent.putExtra("type",socailInfos.get(position).getProjectType());
                    intent.putExtra("projectCode",socailInfos.get(position).getProjectCode());
                    intent.putExtra("selectType",String.valueOf(selectType));
                    intent.putExtra("detailType",String.valueOf(detailType));
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof ProgressViewHolder) {
            ProgressViewHolder progressHolder = (ProgressViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    progressHolder.textshow.setText("上拉加载更多...");
                    progressHolder.textshow.setVisibility(View.GONE);
                    progressHolder.progressBar.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    progressHolder.textshow.setText("正在加载...");
                    progressHolder.progressBar.setVisibility(View.GONE);
                    break;
                case LOAD_NODATA:
                    progressHolder.textshow.setVisibility(View.GONE);
                    progressHolder.progressBar.setVisibility(View.GONE);
                    break;
            }
        } else if (holder instanceof HeaderViewHolder) {
            if (bannerUtils==null) {
                bannerUtils = new BannerUtils(context,((HeaderViewHolder)holder).viewpager,((HeaderViewHolder)holder).groupContain, bannerInfo);
                bannerUtils.startPlayBanner();
            		}else {
                return;
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
     * @param status
     */
    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return socailInfos.size() > 0 ? socailInfos.size() + 2 : 2;
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

    class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.bibiPlatform)
        LinearLayout bibiPlatform;
        @BindView(R.id.finacePlatform)
        LinearLayout finacePlatform;
        @BindView(R.id.tenderPlatform)
        LinearLayout tenderPlatform;
        @BindView(R.id.servicePlatform)
        LinearLayout servicePlatform;
        @BindView(R.id.viewpager)
        MyViewPager viewpager;
        @BindView(R.id.group_contain)
        LinearLayout groupContain;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bibiPlatform.setOnClickListener(this);
            finacePlatform.setOnClickListener(this);
            tenderPlatform.setOnClickListener(this);
            servicePlatform.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, PlatFormActivity.class);
            switch (v.getId()) {
                case R.id.bibiPlatform:
                    intent.putExtra("Type","1");
                    context.startActivity(intent);
                    break;
                case R.id.finacePlatform:
                    intent.putExtra("Type","2");
                    context.startActivity(intent);
                    break;
                case R.id.tenderPlatform:
                    intent.putExtra("Type","3");
                    context.startActivity(intent);
                    break;
                case R.id.servicePlatform:
                    intent.putExtra("Type","4");
                    context.startActivity(intent);
                    break;
            }
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
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

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
