package com.jzhu.io.gank.ui.activity;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.gank.R;

@Route(path = "/about/aboutActivity")
public class AboutActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @OnClick({ R.id.left_icon, R.id.source})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
