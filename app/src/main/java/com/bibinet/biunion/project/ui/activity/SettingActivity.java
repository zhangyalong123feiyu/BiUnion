package com.bibinet.biunion.project.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.utils.DataCleanManagerUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-5.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.accountManage)
    LinearLayout accountManage;
    @BindView(R.id.advicetalk)
    LinearLayout advicetalk;
    @BindView(R.id.clearCache)
    LinearLayout clearCache;
    @BindView(R.id.loginOut)
    Button loginOut;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.cachesize)
    TextView cachesize;
    private String cachePath = Environment.getExternalStorageDirectory() + "/BiUnion/cacheDritory/";
    private File cacheFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("设置");
        titleImageleft.setVisibility(View.VISIBLE);
         cacheFile = new File(cachePath);
        try {
            String sizeInfo = DataCleanManagerUtils.getCacheSize(cacheFile);
            cachesize.setText(sizeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.accountManage, R.id.advicetalk, R.id.clearCache, R.id.loginOut,R.id.title_imageleft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.accountManage:
                break;
            case R.id.advicetalk:
                break;
            case R.id.clearCache:
                doClearCache();
                break;
            case R.id.loginOut:
                break;
            case R.id.title_imageleft:
                finish();
                break;
        }
    }

    private void doClearCache() {
        DataCleanManagerUtils.deleteFolderFile(cachePath, false);
        try {
            cachesize.setText(DataCleanManagerUtils.getCacheSize(cacheFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
    }
}
