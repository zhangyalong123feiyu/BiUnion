package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-7.
 */

public class MoreProjectActivity extends BaseActivity {
    @BindView(R.id.backImage)
    ImageView backImage;
    @BindView(R.id.searchProject)
    EditText searchProject;
    @BindView(R.id.projectIndustry)
    LinearLayout projectIndustry;
    @BindView(R.id.projectAeara)
    LinearLayout projectAeara;
    @BindView(R.id.projectTime)
    LinearLayout projectTime;
    @BindView(R.id.projectRecyler)
    RecyclerView projectRecyler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.backImage, R.id.projectIndustry, R.id.projectAeara, R.id.projectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backImage:
                finish();
                break;
            case R.id.projectIndustry:

                break;
            case R.id.projectAeara:
                break;
            case R.id.projectTime:
                break;
        }
    }
}
