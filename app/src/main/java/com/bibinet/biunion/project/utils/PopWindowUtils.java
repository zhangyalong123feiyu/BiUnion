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

public class PopWindowUtils implements View.OnClickListener {
    private Context context;
    private TextView projectNameOne;
    private TextView projectNameTwo;
    private TextView projectNameThree;
    private TextView projectProvideInfo;
    private TextView projectBuyInfo;
    private TextView projectTenderInfo;
    private TextView projectInfoText;

    public PopWindowUtils(Context context, TextView projectInfo,TextView projectNameOne,TextView projectNameTwo,TextView projectNameThree) {
        this.context = context;
        this.projectInfoText = projectInfo;
        this.projectNameOne=projectNameOne;
        this.projectNameTwo=projectNameTwo;
        this.projectNameThree=projectNameThree;
    }

    public void showPopWindow() {
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        View popview=LayoutInflater.from(context).inflate(R.layout.item_projecttype,null);
        popupWindow.setContentView(popview);
        //点击popupWindow以外的区域自动关闭popupWindow
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAsDropDown(projectInfoText, 0, 0);//设置popwindow的弹出方式为向下弹出
         projectInfoText = (TextView)popview.findViewById(R.id.projectInfo);
         projectTenderInfo = (TextView)popview.findViewById(R.id.tenderInfo);
         projectBuyInfo = (TextView)popview.findViewById(R.id.buyprojectInfo);
         projectProvideInfo = (TextView)popview.findViewById(R.id.provideProjectInfo);
        projectInfoText.setOnClickListener(this);
        projectTenderInfo.setOnClickListener(this);
        projectBuyInfo.setOnClickListener(this);
        projectProvideInfo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tenderInfo:
                projectInfoText.setText(R.string.tenderInfo);
                projectNameOne.setText("招标公告");
                projectNameTwo.setText("中标候选人公示");
                projectNameThree.setText("中标公告");
                break;
            case R.id.projectInfo:
                projectInfoText.setText(R.string.projectInfo);
                projectNameOne.setText("拟在建项目");
                projectNameTwo.setText("业主委托项目");
                projectNameThree.setText("PPP项目");
                break;
            case R.id.buyprojectInfo:
                projectInfoText.setText(R.string.buyProjectInfo);
                projectNameOne.setText("政府采购");
                projectNameTwo.setText("企业采购");
                projectNameThree.setText("");
                break;
            case R.id.provideProjectInfo:
                projectInfoText.setText(R.string.provideProjectInfo);
                projectNameOne.setText("供应商");
                projectNameTwo.setText("采购业主");
                projectNameThree.setText("招标机构");
                break;
        }
    }
}
