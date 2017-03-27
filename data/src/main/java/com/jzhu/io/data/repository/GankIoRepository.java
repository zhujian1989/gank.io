package com.jzhu.io.data.repository;

import com.jzhu.io.data.BaseGankIoResp;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.data.entities.GankFLEntities;
import rx.Observable;

import java.util.List;

public interface GankIoRepository {

    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlist(int rows, int pageNum);

    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlGrils(int num);

    Observable<BaseGankIoResp<List<GankAndroidAndiOSEntities>>> getAndroidList(int rows, int pageNum);

    Observable<BaseGankIoResp<List<GankAndroidAndiOSEntities>>> getIosList(int rows, int pageNum);


}
