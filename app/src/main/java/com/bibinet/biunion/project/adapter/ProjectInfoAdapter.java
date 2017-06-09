/*
package com.bibinet.biunion.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibinet.biunion.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

*/
/**
 * Created by bibinet on 2017-6-1.
 *//*


public class ProjectInfoAdapter extends RecyclerView.Adapter<ProjectInfoAdapter.MyProjectAdapter> {

    private Context context;
    private List<ProjectInfoBean> projectInfoBeen;

    public ProjectInfoAdapter(Context context, List<ProjectInfoBean> projectInfoBeen) {
        this.context = context;
        this.projectInfoBeen = projectInfoBeen;
    }

    @Override
    public MyProjectAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_projectinfo, parent, false);
        MyProjectAdapter myProjectAdapter = new MyProjectAdapter(view);
        return myProjectAdapter;
    }

    @Override
    public void onBindViewHolder(MyProjectAdapter holder, int position) {
        holder.companyName.setText(projectInfoBeen.get(position).getCompanyName());
        holder.projectDescrp.setText(projectInfoBeen.get(position).getCompanyDescrp());
        holder.projectLoaction.setText(projectInfoBeen.get(position).getLoaction());
        holder.projectTypeImage.setImageResource(R.mipmap.ic_launcher);
        holder.projectTime.setText(projectInfoBeen.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return projectInfoBeen.size()==0?0:projectInfoBeen.size();
    }

    class MyProjectAdapter extends RecyclerView.ViewHolder {
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
        public MyProjectAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
*/
