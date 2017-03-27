package com.jzhu.io.baselibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.jzhu.io.baselibrary.injection.component.ApplicationComponent;
import com.jzhu.io.baselibrary.injection.module.ActivityModule;
import com.jzhu.io.baselibrary.utils.ToastUtils;

public abstract class BaseFragment extends Fragment {
//    private ProgressDialog progressDialog;
    private View mRootView;

    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    public Context getContext(){
        return mContext;
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
    public void startActivity(Class<? extends Activity> activityClass, Bundle bd) {
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
        Toast.makeText(getActivity(), msg + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * 以较长的时间来toast显示，大约5秒钟显示。
     *
     * @param msg
     */
    public void showLongMessage(String msg) {
        ToastUtils.showLongMessage(getActivity(), msg);
    }

    /**
     * 显示消息提示，避免重复提示
     *
     * @param msg
     */
    public void showToast(String msg) {
        ToastUtils.showShortMessage(getActivity(), msg);
    }

    public void showToast(int msgId){
        ToastUtils.showLongMessage(getActivity(),msgId);
    }


    /**
     * 获取当前程序中的本地目标
     *
     * @param localIntent
     * @return
     */
    public Intent getLocalIntent(Class<? extends Context> localIntent, Bundle bd) {
        Intent intent = new Intent(getActivity(), localIntent);
        if (null != bd) {
            intent.putExtras(bd);
        }
        return intent;
    }


    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication)getActivity().getApplication()).getApplicationComponent();
    }
    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }

    public void showLoading(String msg) {
//        if (progressDialog == null) {
//            progressDialog = CustomizedProgressDialog.createInstance(getActivity());
//        }
//        progressDialog.setMessage(msg);
//        progressDialog.show();
    }

    public void showLoading() {
//        if (progressDialog == null) {
//            progressDialog = CustomizedProgressDialog.createInstance(getActivity());
//        }
//        progressDialog.setMessage("");
//        progressDialog.show();
    }

    public void hideLoading() {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initContentView(savedInstanceState);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
