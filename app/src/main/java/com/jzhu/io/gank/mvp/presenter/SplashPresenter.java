package com.jzhu.io.gank.mvp.presenter;

import com.jzhu.io.baselibrary.BaseAbstractPresenter;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.baselibrary.BaseApplication;
import com.jzhu.io.baselibrary.BaseSubscriber;
import com.jzhu.io.data.common.Constant;
import com.jzhu.io.data.service.GankIoService;
import com.jzhu.io.data.utils.PreferenceUtil;
import com.jzhu.io.gank.mvp.view.SplashView;

import javax.inject.Inject;

/**
 * Created by jzhu on 2016/11/22.
 */

public class SplashPresenter extends BaseAbstractPresenter<SplashView> {

    @Inject
    GankIoService gankIoService;

    @Inject
    public SplashPresenter() {
    }

    public void getList(int num, BaseActivity act) {
        if (!checkNetWork()) {
            return;
        }
        mView.showLoading();
        gankIoService.execute(new BaseSubscriber<String>(mView) {
            @Override
            public void onNext(String url) {
                mView.getGrils(url);
                gankIoService.unsubscribe();
            }
        }, gankIoService.getGrils(num),act);
    }

    public String getLastUrl() {
        return PreferenceUtil.getString(BaseApplication.getContext(), Constant.SHARED_PREFERENCES,Constant.SP_KEY_SPLASH_URL,null);
    }
}
