package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-14.
 */

public class WriteTenderBook extends BaseActivity implements View.OnClickListener,DialogUtils.DialogListioner{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.writeBookInput)
    TextView writeBookInput;
    @BindView(R.id.projectTypeInput)
    TextView projectTypeInput;
    @BindView(R.id.selectTenderType)
    TextView selectTenderType;
    @BindView(R.id.tenderTypeInput)
    TextView tenderTypeInput;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.contanctPersonInput)
    EditText contanctPersonInput;
    @BindView(R.id.contactTypeInput)
    EditText contactTypeInput;
    @BindView(R.id.emailInput)
    EditText emailInput;
    @BindView(R.id.saveTenderBook)
    Button saveTenderBook;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text1)
    TextView text1;
    private DialogUtils dialogUtils=new DialogUtils();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writebook);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("代写标书");
        titleImageleft.setVisibility(View.VISIBLE);
        titleImageright.setVisibility(View.VISIBLE);
        titleImageright.setImageResource(R.mipmap.daixiebiaoshu_lishijilv);
        dialogUtils.setDialoglistioner(this);
    }

    @OnClick({R.id.title_imageright, R.id.title_imageleft,R.id.writeBookInput, R.id.projectTypeInput, R.id.selectTenderType, R.id.tenderTypeInput, R.id.saveTenderBook})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageright:
                startActivity(new Intent(this, TenderHistoryActivity.class));
                break;
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.writeBookInput:
                dialogUtils.showBottomDiaglog(this,R.layout.item_bottomtenderbookselectdialog);
                break;
            case R.id.projectTypeInput:
                dialogUtils.showBottomDiaglog(this,R.layout.item_bottomprojecttypedialog);
                break;
            case R.id.selectTenderType:
                dialogUtils.showBottomDiaglog(this,R.layout.item_bottomtenderbooktypedialog);
                break;
            case R.id.tenderTypeInput:
                dialogUtils.showBottomDiaglog(this,R.layout.item_bottomtendertypedialog);
                break;
            case R.id.saveTenderBook:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }
//对话框点击事件监听
    @Override
    public void onDialogClickListioner(View view) {
        	switch (view.getId()) {
        			case R.id.TenderBook://招标文件
                        writeBookInput.setText("招标文件");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.bidDoucments://投标文件
                        writeBookInput.setText("投标文件");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.governmentBuy://政府采购
                        projectTypeInput.setText("政府采购");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.noGovernmentBuy://非政府采购
                        projectTypeInput.setText("非政府采购");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.project://工程
                        selectTenderType.setText("工程");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.goods://货物
                        selectTenderType.setText("货物");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.publishTender://公开招标
                        tenderTypeInput.setText("公开招标");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.useLegalTender://依法招标
                        tenderTypeInput.setText("依法招标");
                        dialogUtils.disBottomDiaglog();
        				break;
        			case R.id.askTender://邀请招标
                        tenderTypeInput.setText("邀请招标");
                        dialogUtils.disBottomDiaglog();
        				break;

        			default:
        				break;
        			}
    }
}
