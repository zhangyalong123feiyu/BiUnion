package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-14.
 */

public class CoustomAskActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.noMsgRela)
    RelativeLayout noMsgRela;
    @BindView(R.id.msgRecyclerView)
    RecyclerView msgRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustomask);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("客服咨询");
        titleImageleft.setVisibility(View.VISIBLE);
        titleImageright.setVisibility(View.VISIBLE);
        titleImageright.setImageResource(R.mipmap.kefuzixun_kefu);
    }

    @OnClick({R.id.title_imageright, R.id.title_imageleft, R.id.noMsgRela, R.id.msgRecyclerView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageright:
                break;
            case R.id.title_imageleft:
                finish();
                break;
            case R.id.noMsgRela:
                break;
            case R.id.msgRecyclerView:
                break;
        }
    }
}
