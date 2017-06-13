package com.bibinet.biunion.project.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bibinet.biunion.R;

/**
 * Created by bibinet on 2017-4-22.
 */

public class DialogUtils {
    private DialogListioner dialogListioner;
    private View view;
    private AlertDialog dialog;
    private ProgressDialog progressDialog;

    public View getView(){
       return view;
    }
    public void diloagShow(Context context, int ResuoreceId){
        AlertDialog.Builder dialogBuider=new AlertDialog.Builder(context);
         dialog = dialogBuider.create();
         view= LayoutInflater.from(context).inflate(ResuoreceId,null);
        dialog.show();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListioner.onDialogClickListioner(v);
            }
        });

    }
    public void dialogDismiss(){
        dialog.dismiss();
    }
    public void setDialoglistioner(DialogListioner listioner){
        this.dialogListioner=listioner;
    }
   public interface DialogListioner{
        void onDialogClickListioner(View view);
    }
    public void showLoginDialog(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("正在登陆");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        progressDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        progressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        progressDialog.setIcon(R.mipmap.ic_launcher);//
        progressDialog.show();
    }
    public void disLoginDialog(){
        progressDialog.dismiss();
    }
}
