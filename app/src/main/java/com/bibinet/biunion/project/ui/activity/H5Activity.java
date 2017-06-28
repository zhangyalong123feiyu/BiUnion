package com.bibinet.biunion.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bibinet.biunion.R;
import com.bibinet.biunion.mvp.presenter.H5ActivityPresenter;
import com.bibinet.biunion.mvp.view.H5ActivityView;
import com.bibinet.biunion.project.application.BaseActivity;
import com.bibinet.biunion.project.application.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-6-10.
 */

public class H5Activity extends BaseActivity implements H5ActivityView{
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
    @BindView(R.id.title_imageRightFoucs)
    ImageView titleImageRightFoucs;
    private H5ActivityPresenter h5ActivityPresenter;
    private String type;
    private String projectCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        h5ActivityPresenter=new H5ActivityPresenter(this);
        Intent intent = getIntent();
        String detailUrl = intent.getStringExtra("detailUrl");
         type = intent.getStringExtra("type");
         projectCode = intent.getStringExtra("projectCode");
        webview.loadUrl(Constants.baseUrl + detailUrl);
        titleImageleft.setVisibility(View.VISIBLE);
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
   @OnClick({R.id.title_imageleft,R.id.title_imageright,R.id.title_imageRightFoucs})
        public void viewOnclick(View view){
       	switch (view.getId()) {
       			case R.id.title_imageleft:
       				finish();
       				break;

       			case R.id.title_imageright:/*客服*/
                    startActivity(new Intent(this, ChatActivity.class));
       				break;

       			case R.id.title_imageRightFoucs:/*关注*/
                    Log.i("TAG","点击关注");
                    if (titleImageRightFoucs.isSelected()) {
                    			titleImageRightFoucs.setSelected(false);
                            h5ActivityPresenter.cancelFoucs(100761,projectCode);
                    		}else
                    		    {
                    titleImageRightFoucs.setSelected(true);
                    h5ActivityPresenter.collctionData(100761,projectCode,1);
                }

       				break;

       			default:
       				break;
       			}
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

    @Override
    public void showProgress() {

    }
    @Override
    public void hideProgress() {

    }
    @Override
    public void onCollectoinSucess() {
        Toast.makeText(this,"恭喜您已经收藏成功了",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCollectionFailed(String msg) {
        Toast.makeText(this,"收藏失败",Toast.LENGTH_SHORT).show();
        Log.i("TAG","shibaishoucang"+msg);
    }

    @Override
    public void onCancelFoucsSucess() {
        Toast.makeText(this,"取消收藏成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelFoucsFailed() {
        Toast.makeText(this,"取消收藏失败",Toast.LENGTH_SHORT).show();
    }
}
