package com.jzhu.io.gank.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import butterknife.BindView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jzhu.io.baselibrary.BaseMvpActivity;
import com.jzhu.io.baselibrary.utils.delay.DelayUtils;
import com.jzhu.io.baselibrary.utils.glide.ImageLoadConfig;
import com.jzhu.io.baselibrary.utils.glide.ImageLoader;
import com.jzhu.io.gank.R;
import com.jzhu.io.gank.injection.component.DaggerGankIoComponent;
import com.jzhu.io.gank.injection.module.GankIoModule;
import com.jzhu.io.gank.mvp.presenter.SplashPresenter;
import com.jzhu.io.gank.mvp.view.SplashView;

/**
 * Created by zhujian on 2017/3/26.
 */

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashView {

    @BindView(R.id.img)
    ImageView mImageView;

    private DelayUtils mDelayUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        //fullScreen();
        loaddUrl();
        delay();
    }

    private void fullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void loaddUrl() {
        mPresenter.getList(1, this);
    }

    private void delay() {
        mDelayUtils = new DelayUtils();
        mDelayUtils.delay(2000, new DelayUtils.OnDelayListener() {
            @Override
            public void onDelayFinish() {
                ARouter.getInstance().build("/main/mainActivity").navigation();
                finish();
            }

            @Override
            public void onDelaying() {

            }
        });
    }

    @Override
    public void getGrils(String url) {
        ImageLoader.loadStringRes(mImageView, url, ImageLoadConfig.parseBuilder(ImageLoader.defConfig).setAnimResId(R.anim.fade_in).build(), null);
    }

    @Override
    protected void injectComponent() {
        DaggerGankIoComponent.builder()
                             .applicationComponent(getApplicationComponent())
                             .gankIoModule(new GankIoModule())
                             .activityModule(getActivityModule())
                             .build()
                             .inject(this);
        mPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mDelayUtils) {
            mDelayUtils.cancel();
        }
    }

}
