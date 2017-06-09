package com.bibinet.biunion.project.adapter;

import android.content.Context;


import com.bibinet.biunion.R;
import com.bibinet.biunion.project.bean.cityselectbean.CityBean;
import com.bibinet.biunion.project.utils.cityselectutil.CommonAdapter;
import com.bibinet.biunion.project.utils.cityselectutil.Constance;
import com.bibinet.biunion.project.utils.cityselectutil.ViewHolder;

import java.util.List;

/**
 * Created by zp on 2016/12/13.
 */

public class CityAdapter extends CommonAdapter<CityBean> {
    private int locateState= Constance.LOCATING;
    public CityAdapter(Context context, int layoutId, List<CityBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, CityBean cityBean) {
        holder.setText(R.id.tvCity,cityBean.getCityName());

    }


}
