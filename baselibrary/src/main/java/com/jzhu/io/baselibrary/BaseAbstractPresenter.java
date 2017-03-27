package com.jzhu.io.baselibrary;

import android.content.Context;
import com.jzhu.io.baselibrary.utils.NetWorkUtils;

import javax.inject.Inject;

/**
 * Created by jzhu on 2016/11/22.
 */

public class BaseAbstractPresenter<T extends BaseView> implements BasePresenter<T> {
    @Inject
    Context mContext;

    protected T mView;

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    public void resume() {

    }

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    public void pause() {

    }

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onStop() method.
     */
    public void stop() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    public void destroy() {
        mView = null;
    }

    public void setView(T view) {
        this.mView = view;
    }


    public boolean checkNetWork() {

        if (!NetWorkUtils.isNetWorkAvailable(mContext)) {
            mView.onError("请检查网络");
            return false;
        }

        return true;
    }

}
