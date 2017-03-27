package com.jzhu.io.baselibrary;

import android.os.Bundle;

import javax.inject.Inject;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements
                                                                                          BaseView{

//    protected ProgressDialog mProgressDialog;

    @Inject
    protected T mPresenter;

    protected abstract void injectComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mProgressDialog = ProgressDialog.createDialog(this);
        injectComponent();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
//        mProgressDialog = null;
    }

    @Override
    public void showLoading() {
//        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
//        if (mProgressDialog != null)
//        mProgressDialog.dismiss();
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    public void onSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void onThrowable(Throwable e) {

    }
}
