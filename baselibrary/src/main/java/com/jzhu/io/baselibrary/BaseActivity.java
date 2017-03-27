package com.jzhu.io.baselibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.jzhu.io.baselibrary.common.AppManager;
import com.jzhu.io.baselibrary.injection.component.ApplicationComponent;
import com.jzhu.io.baselibrary.injection.module.ActivityModule;
import com.jzhu.io.baselibrary.utils.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * 窗口基类
 */
@SuppressLint("NewApi")
public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        this.getApplicationComponent().inject(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initContentView(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 以无参数的模式启动Activity。
     *
     * @param activityClass
     */
    public void startActivity(Class<? extends Activity> activityClass) {
        startActivity(getLocalIntent(activityClass, null));
        //   me.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 以绑定参数的模式启动Activity。
     *
     * @param activityClass
     */
    public void startActivity(Class<? extends Activity> activityClass,
                              Bundle bd) {
        startActivity(getLocalIntent(activityClass, bd));
        //    me.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 在底部显示一条toast信息,大约3秒钟时间。<br>
     * 若想让toast显示时间较长，请调用showLongMessage
     *
     * @param msg
     */
    public void showMessage(Object msg) {
        Toast.makeText(this, msg + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * 以较长的时间来toast显示，大约5秒钟显示。
     *
     * @param msg
     */
    public void showLongMessage(String msg) {
        ToastUtils.showLongMessage(this, msg);
    }

    /**
     * 显示消息提示，避免重复提示
     *
     * @param msg
     */
    public void showToast(String msg) {
        ToastUtils.showShortMessage(this, msg);
    }

    public void showToast(int msgId) {
        ToastUtils.showLongMessage(this, msgId);
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);

        super.onDestroy();
    }

    /**
     * 获取当前程序中的本地目标
     *
     * @param localIntent
     * @return
     */
    public Intent getLocalIntent(Class<? extends Context> localIntent,
                                 Bundle bd) {
        Intent intent = new Intent(this, localIntent);
        if (null != bd) {
            intent.putExtras(bd);
        }
        return intent;
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected View getContentView() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        FrameLayout
                content =
                (FrameLayout) view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    //处理bundle数据
    protected abstract void initContentView(Bundle savedInstanceState);
}
