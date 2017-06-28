package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-15.
 */

public class PlatFormActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform);
        ButterKnife.bind(this);
        initView();

    }
    private void initView() {
        title.setText("比比平台");
        Intent intent=getIntent();
        String type = intent.getStringExtra("Type");
        switch (Integer.parseInt(type)) {
        			case 1:
                        webview.loadUrl(Constants.baseUrl + "pis/appPage/detail.jsp");
        				break;
        			case 2:
                        webview.loadUrl(Constants.baseUrl + "pis/appPage/detail1.jsp");
        				break;
        			case 3:
                        webview.loadUrl(Constants.baseUrl + "pis/appPage/detail2.jsp");
        				break;
        			case 4:
                        webview.loadUrl(Constants.baseUrl + "pis/appPage/detail3.jsp");
        				break;

        			default:
        				break;
        			}

        webview.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings seting = webview.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }

            }
        });

    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webview.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                //    System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }
}
