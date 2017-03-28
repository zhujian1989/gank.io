package com.jzhu.io.gank.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.baselibrary.BaseMvpFragment;
import com.jzhu.io.baselibrary.utils.ObjectUtils;
import com.jzhu.io.data.entities.GankFLEntities;
import com.jzhu.io.gank.R;
import com.jzhu.io.gank.injection.component.DaggerGankIoComponent;
import com.jzhu.io.gank.injection.module.GankIoModule;
import com.jzhu.io.gank.mvp.presenter.FLPresenter;
import com.jzhu.io.gank.mvp.view.FLView;
import com.jzhu.io.gank.ui.adapter.FLAdapter;

import java.util.List;

/**
 * Created by zhujian on 2017/3/26.
 */

public class FLFragment extends BaseMvpFragment<FLPresenter> implements FLView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;

    private FLAdapter mAdapter;

    private int pageNum = 1;

    private int pageRow = 10;

    private boolean isSlideUp = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fl;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initRecyclerView();
        initRefreshLayout();
        mRefreshLayout.beginRefreshing();
    }

    private void initRecyclerView() {
        mAdapter = new FLAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(BGADivider.newShapeDivider().setSizeDp(getResources().getDimensionPixelSize(R.dimen.common_divider_height)).setColor(getResources().getColor(R.color.transparent),false));
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
    public void getList(List<GankFLEntities> list) {
        stopRL();
        if (ObjectUtils.isListEmpty(list)) {
            return;
        }
        if (isSlideUp) {
            mAdapter.addMoreData(list);
        }
        else {
            mAdapter.addNewData(list);
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
        mPresenter.getList(pageRow, pageNum, (BaseActivity) getActivity());
    }

    private boolean loadMore() {
        isSlideUp = true;
        pageNum++;
        mPresenter.getList(pageRow, pageNum, (BaseActivity) getActivity());
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

}
