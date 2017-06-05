package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.bibinet.biunion.R;
import com.bibinet.biunion.project.bean.ProjectInfoBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-6-1.
 */

public class ProjectInfoAdapterx extends BaseRecyclerAdapter<ProjectInfoAdapterx.MyProjectViewHolder> {

    private Context context;
    private List<ProjectInfoBean> projectInfoBeen;

    public ProjectInfoAdapterx(Context context, List<ProjectInfoBean> projectInfoBeen) {
        this.context = context;
        this.projectInfoBeen = projectInfoBeen;
    }

    @Override
    public MyProjectViewHolder getViewHolder(View view) {
        return new MyProjectViewHolder(view,false);
    }

    public void setData(List<ProjectInfoBean> projectInfoBeen) {
        this.projectInfoBeen = projectInfoBeen;
        notifyDataSetChanged();
    }

    @Override
    public MyProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_projectinfo,parent,false);
        MyProjectViewHolder myProjectViewHolder=new MyProjectViewHolder(view, true);
        return myProjectViewHolder;
    }

    @Override
    public void onBindViewHolder(MyProjectViewHolder holder, int position, boolean isItem) {
            holder.companyName.setText(projectInfoBeen.get(position).getCompanyName());
            holder.projectDescrp.setText(projectInfoBeen.get(position).getCompanyDescrp());
            holder.projectLoaction.setText(projectInfoBeen.get(position).getLoaction());
            holder.projectTime.setText(projectInfoBeen.get(position).getTime());
            ImageOptions imageOptions=new ImageOptions.Builder().setCircular(true).setFailureDrawableId(R.mipmap.ic_launcher).build();
            x.image().bind(holder.projectTypeImage,projectInfoBeen.get(position).getPicUrl(),imageOptions);
    }

    @Override
    public int getAdapterItemCount() {
        return projectInfoBeen.size();
    }
    public void insert(ProjectInfoBean projectInfo, int position) {
        insert(projectInfoBeen, projectInfo, position);
    }

    public void remove(int position) {
        remove(projectInfoBeen, position);
    }

    public void clear() {
        clear(projectInfoBeen);
    }

    class MyProjectViewHolder extends RecyclerView.ViewHolder{
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
        public MyProjectViewHolder(View itemView,boolean isItem) {
            super(itemView);
            if (isItem) {
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
