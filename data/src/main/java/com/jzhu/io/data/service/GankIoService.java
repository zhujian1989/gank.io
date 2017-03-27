package com.jzhu.io.data.service;

import com.jzhu.io.data.BaseService;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.data.entities.GankFLEntities;
import rx.Observable;

import java.util.List;

public abstract class GankIoService extends BaseService {

    public abstract Observable<List<GankFLEntities>> getList(int rows, int pageNum );

    public abstract Observable<String> getGrils(int num);

    public abstract Observable<List<GankAndroidAndiOSEntities>> getAndroid(int rows, int pageNum );

    public abstract Observable<List<GankAndroidAndiOSEntities>> getIos(int rows, int pageNum );

}
