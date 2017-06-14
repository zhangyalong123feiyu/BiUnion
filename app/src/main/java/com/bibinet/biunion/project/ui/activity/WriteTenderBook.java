package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-14.
 */

public class WriteTenderBook extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;

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
    }

    @OnClick({R.id.title_imageright, R.id.title_imageleft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_imageright:
                startActivity(new Intent(this, TenderHistoryActivity.class));
                break;
            case R.id.title_imageleft:
                finish();
                break;
        }
    }
}
