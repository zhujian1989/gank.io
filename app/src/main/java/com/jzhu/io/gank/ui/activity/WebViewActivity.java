package com.jzhu.io.gank.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.gank.R;
import com.jzhu.io.gank.ui.widget.LJWebView;
import com.jzhu.io.gank.ui.widget.TitleBar;

@Route(path = "/web/webViewActivity")
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    @BindView(R.id.webview)
    LJWebView mLJWebView;

    @Autowired
    String url;

    @Autowired
    String title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        loadWebView(url, title);
    }

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    private void loadWebView(String url, String name) {
        mTitleBar.setTitle(name);
        mLJWebView.setBarHeight(6);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mLJWebView.setMixLoad();
        }
        mLJWebView.setClickable(true);
        mLJWebView.setUseWideViewPort(true);
        mLJWebView.setSupportZoom(true);
        mLJWebView.setBuiltInZoomControls(true);
        mLJWebView.setJavaScriptEnabled(true);
        mLJWebView.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mLJWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mLJWebView.loadUrl(url);
    }

    @OnClick({ R.id.left_icon })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLJWebView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLJWebView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLJWebView.finish();
    }
}
