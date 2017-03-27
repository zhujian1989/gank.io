package com.jzhu.io.gank.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jzhu.io.gank.R;

public class LJWebView extends RelativeLayout {

	private Context context;

	private WebView mWebView = null; //
	private ProgressBar progressBar = null; // 水平进度条
	private int barHeight = 8; // 水平进度条的高
	private boolean isAdd = false; // 判断是否已经加入进度条
	private boolean isLoadFinish = false;
	private Bitmap mFavicon;

	public LJWebView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public LJWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public LJWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	private void init() {
		mWebView = new WebView(context);
		this.addView(mWebView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100) {
					progressBar.setVisibility(View.GONE);
					isLoadFinish = true;
				} else {
					if (!isAdd) {
						progressBar = (ProgressBar) LayoutInflater
								.from(context).inflate(
                                        R.layout.progress_horizontal, null);
						progressBar.setMax(100);
						progressBar.setProgress(0);
						LJWebView.this.addView(progressBar,
								LayoutParams.MATCH_PARENT, barHeight);
						isAdd = true;
					}
					progressBar.setVisibility(View.VISIBLE);
					progressBar.setProgress(newProgress);
				}
			}
			@Override
			public void onReceivedTitle(WebView view, String title) {
				// TODO Auto-generated method stub
				super.onReceivedTitle(view, title);
			}

			@Override
			public void onReceivedIcon(WebView view, Bitmap icon) {
				super.onReceivedIcon(view, icon);
				mFavicon = icon;
			}

		});

	}

	public Bitmap getFavicon() {
		return mFavicon;
	}

	public boolean isLoadFinish() {
		return isLoadFinish;
	}

	public void finish() {
		mWebView.onPause();
		mWebView.stopLoading();
		long timeout = ViewConfiguration.getZoomControlsTimeout();
		postDelayed(new Runnable() {
			@Override
			public void run() {
				mWebView.destroy();
			}
		}, timeout);
	}

	public void reload() {
		mWebView.reload();
	}

	public void goBack() {
		mWebView.goBack();
	}

	public boolean canGoBack() {
		return mWebView.canGoBack();
	}

	public void setBarHeight(int height) {
		barHeight = height;
	}

	public void setClickable(boolean value) {
		mWebView.setClickable(value);
	}

	public void setUseWideViewPort(boolean value) {
		mWebView.getSettings().setUseWideViewPort(value);
	}

	public void setSupportZoom(boolean value) {
		mWebView.getSettings().setSupportZoom(value);
	}

	public void setBuiltInZoomControls(boolean value) {
		mWebView.getSettings().setBuiltInZoomControls(value);
	}

	public void setJavaScriptEnabled(boolean value) {
		mWebView.getSettings().setJavaScriptEnabled(value);
	}

	public void setCacheMode(int value) {
		mWebView.getSettings().setCacheMode(value);
	}

	public void setWebViewClient(WebViewClient value) {
		mWebView.setWebViewClient(value);
	}

	public void loadUrl(String url) {
		mWebView.loadUrl(url);
	}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setMixLoad(){
    mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    }

	public WebView getWebView() {
		return mWebView;
	}

	public void resume() {
		mWebView.onResume();
		mWebView.reload();
	}

	public void pause() {
		mWebView.onPause();
	}
}
