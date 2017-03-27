package com.jzhu.io.data.repository.impl;

import com.jzhu.io.data.BaseGankIoResp;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.data.entities.GankFLEntities;
import com.jzhu.io.data.net.RetrofitFactory;
import com.jzhu.io.data.net.api.GankIoApi;
import com.jzhu.io.data.repository.GankIoRepository;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

public class GankIoRepositoryImpl implements GankIoRepository {

    @Inject
    public GankIoRepositoryImpl() {
    }

    @Override
    public Observable<BaseGankIoResp<List<GankFLEntities>>> getFlist(int rows, int pageNum) {
        return RetrofitFactory.getInstance().create(GankIoApi.class).getFlList(rows, pageNum);
    }

    @Override
    public Observable<BaseGankIoResp<List<GankFLEntities>>> getFlGrils(int num) {
        return RetrofitFactory.getInstance().create(GankIoApi.class).getFlGrils(num);
    }

    @Override
    public Observable<BaseGankIoResp<List<GankAndroidAndiOSEntities>>> getAndroidList(int rows,int pageNum) {
        return RetrofitFactory.getInstance().create(GankIoApi.class).getAndroidList(rows, pageNum);
    }

    @Override
    public Observable<BaseGankIoResp<List<GankAndroidAndiOSEntities>>> getIosList(int rows,int pageNum) {
        return RetrofitFactory.getInstance().create(GankIoApi.class).getiOSList(rows, pageNum);
    }
}
