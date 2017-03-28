package com.jzhu.io.gank.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.baselibrary.BaseMvpFragment;
import com.jzhu.io.baselibrary.utils.ObjectUtils;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.gank.R;
import com.jzhu.io.gank.injection.component.DaggerGankIoComponent;
import com.jzhu.io.gank.injection.module.GankIoModule;
import com.jzhu.io.gank.mvp.presenter.AndroidPresenter;
import com.jzhu.io.gank.mvp.view.AndroidView;
import com.jzhu.io.gank.ui.adapter.AIAdapter;

import java.util.List;

/**
 * Created by zhujian on 2017/3/26.
 */

public class AndroidFragment extends BaseMvpFragment<AndroidPresenter> implements AndroidView, BGARefreshLayout.BGARefreshLayoutDelegate,BGAOnRVItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;

    private AIAdapter mAdapter;

    private int pageNum = 1;

    private int pageRow = 20;

    private boolean isSlideUp = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initRecyclerView();
        initRefreshLayout();
        mRefreshLayout.beginRefreshing();
    }

    private void initRecyclerView() {
        mAdapter = new AIAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(BGADivider.newShapeDivider().setSizeDp(getResources().getDimensionPixelSize(R.dimen.common_divider_height)).setColor(getResources().getColor(R.color.transparent), false));
        mAdapter.setOnRVItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setDelegate(this);
        BGARefreshViewHolder viewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        mRefreshLayout.setRefreshViewHolder(viewHolder);
    }

    @Override
    public void onThrowable(Throwable e) {
        stopRL();
    }

    @Override
    public void getList(List<GankAndroidAndiOSEntities> list) {
        stopRL();
        if (ObjectUtils.isListEmpty(list)) {
            return;
        }
        if (isSlideUp) {
            mAdapter.addMoreData(list);
        }
        else {
            mAdapter.setData(list);
        }
    }

    private void stopRL(){
        if (isSlideUp) {
            mRefreshLayout.endLoadingMore();
        }
        else {
            mRefreshLayout.endRefreshing();
        }
    }

    private void refreshing() {
        isSlideUp = false;
        pageNum = 1;
        mPresenter.getAndroidList(pageRow, pageNum, (BaseActivity) getActivity());
    }

    private boolean loadMore() {
        isSlideUp = true;
        pageNum++;
        mPresenter.getAndroidList(pageRow, pageNum, (BaseActivity) getActivity());
        return false;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return loadMore();
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
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        GankAndroidAndiOSEntities model  = (GankAndroidAndiOSEntities) itemView.getTag();
        ARouter.getInstance().build("/web/webViewActivity").withString("url",model.getUrl()).withString("title",model.getDesc()).navigation();
    }
}
