package com.bibinet.biunion.project.utils.cityselectutil;

import android.view.View;
import android.view.ViewGroup;

/**
 * 通用的RecyclerView 的ItemClickListener，包含点击和长按
 * Created by zp.
 * Date: 16/03/11
 */
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}