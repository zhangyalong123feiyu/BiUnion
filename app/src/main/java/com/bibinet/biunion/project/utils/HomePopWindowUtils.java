package com.bibinet.biunion.project.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-2.
 */
//首页弹出工具类
public class HomePopWindowUtils  {
    private Context context;
    private TextView projectInfo;
    private TextView popwProjectInfo;
    private View popview;
    private PopupWindow popupWindow;

    public HomePopWindowUtils(Context context, TextView projectInfo/*, TextView projectNameOne, TextView projectNameTwo, TextView projectNameThree*/) {
        this.context = context;
        this.projectInfo = projectInfo;
    }
    public View getPopView(){
        if (popview!=null) {
            return popview;
        }else {
            return null;
        }
    }
    public void disMissPopWindow(){
        popupWindow.dismiss();
    }
    public void showPopWindow() {
         popupWindow = new PopupWindow(context);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
         popview=LayoutInflater.from(context).inflate(R.layout.item_projecttype,null);
        popupWindow.setContentView(popview);
        //点击popupWindow以外的区域自动关闭popupWindow
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAsDropDown(projectInfo, 0, 0);//设置popwindow的弹出方式为向下弹出
    }
}
