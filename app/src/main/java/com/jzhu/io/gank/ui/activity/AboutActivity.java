package com.jzhu.io.gank.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.gank.R;

@Route(path = "/about/aboutActivity")
public class AboutActivity extends BaseActivity {

    @BindView(R.id.hide_fun)
    TextView mHideFun;

    @BindView(R.id.about_award_intro)
    TextView mAboutAwardIntro;

    @BindView(R.id.wxpay)
    ImageView mWxpay;

    @BindView(R.id.alipay)
    ImageView mAlipay;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @OnClick({ R.id.left_icon, R.id.source,R.id.hide_fun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hide_fun:
                mHideFun.setText(R.string.about_award);
                mAboutAwardIntro.setVisibility(View.VISIBLE);
                mWxpay.setVisibility(View.VISIBLE);
                mAlipay.setVisibility(View.VISIBLE);
                break;
            case R.id.left_icon:
                finish();
                break;
            case R.id.source:
                ARouter.getInstance()
                       .build("/web/webViewActivity")
                       .withString("url", "https://github.com/zhujian1989/gank.io")
                       .withString("title", getResources().getString(R.string.about_github_link))
                       .navigation();
                break;
        }
    }

}
