package com.bibinet.biunion.project.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bibinet.biunion.R;

/**
 * Created by bibinet on 2017-6-9.
 */

public class PublicPopWindowUtils {
    private Context context;
    private PopOnclickListioner popOnclickListioner;
    private View popview;
    private PopupWindow popupWindow;

    public PublicPopWindowUtils(Context context) {
        this.context = context;
    }
    public void showPopWindow(int layoutId) {
         popupWindow = new PopupWindow(context);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(500);
        popview= LayoutInflater.from(context).inflate(layoutId,null);
        popupWindow.setContentView(popview);
        //点击popupWindow以外的区域自动关闭popupWindow
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

    }
    public void showPopWindow(View view){
        popupWindow.showAsDropDown(view, 0, 0);//设置popwindow的弹出方式为向下弹出
    }
    public void dissMisPopWindow(){
        popupWindow.dismiss();
    }
    public View getPopview() {
        return popview;
    }

     interface PopOnclickListioner{
        void onPopClickListioner();
    }
}
