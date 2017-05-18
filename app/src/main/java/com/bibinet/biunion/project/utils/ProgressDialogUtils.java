package com.bibinet.biunion.project.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.bibinet.biunion.R;


/**
 * Created by bibinet on 2017-5-3.
 */

public class ProgressDialogUtils {
    private ProgressDialog dialog;
    public void showProgressDialog(Context context){
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(false);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setIcon(R.mipmap.ic_launcher);//
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
        dialog.setTitle("正在登陆中。。。");
        dialog.show();
    }
    public void hideProgressDialgo(){
        dialog.dismiss();
    }

}
