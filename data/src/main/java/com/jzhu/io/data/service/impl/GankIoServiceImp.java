package com.jzhu.io.data.service.impl;

import android.text.TextUtils;
import com.jzhu.io.baselibrary.BaseApplication;
import com.jzhu.io.baselibrary.utils.ObjectUtils;
import com.jzhu.io.data.BaseGankIoFunc;
import com.jzhu.io.data.BaseGankIoResp;
import com.jzhu.io.data.common.Constant;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.data.entities.GankFLEntities;
import com.jzhu.io.data.exception.BusinessGankIoException;
import com.jzhu.io.data.repository.GankIoRepository;
import com.jzhu.io.data.service.GankIoService;
import com.jzhu.io.data.utils.PreferenceUtil;
import rx.Observable;
import rx.functions.Func1;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jzhu on 2016/11/22.
 */

public class GankIoServiceImp extends GankIoService {
    @Inject
    GankIoRepository gankIoRepository;

    @Inject
    public GankIoServiceImp() {
    }

    @Override
    public Observable<List<GankFLEntities>> getList(int rows, int pageNum) {
        return gankIoRepository.getFlist(rows, pageNum).flatMap(new BaseGankIoFunc());
    }

    @Override
    public Observable<String> getGrils(int num) {
        return gankIoRepository.getFlGrils(num).flatMap(new Func1<BaseGankIoResp<List<GankFLEntities>>, Observable<String>>() {
            @Override
            public Observable<String> call(BaseGankIoResp<List<GankFLEntities>> listBaseGankIoResp) {
                if (!listBaseGankIoResp.getError()) {
                    return Observable.just(listBaseGankIoResp.getResults()).flatMap(new Func1<List<GankFLEntities>, Observable<String>>() {
                        @Override
                        public Observable<String> call(List<GankFLEntities> gankFLEntities) {
                            String url = null;
                            if (ObjectUtils.isListEmpty(gankFLEntities)) {
                                url = PreferenceUtil.getString(BaseApplication.getContext(), Constant.SHARED_PREFERENCES, Constant.SP_KEY_SPLASH_URL, null);
                                if (TextUtils.isEmpty(url)) {
                                    Observable.error(new BusinessGankIoException());
                                }
                                else {
                                    return Observable.just(url);
                                }
                            }
                            else {
                                url = gankFLEntities.get(0).getUrl();
                                PreferenceUtil.setString(BaseApplication.getContext(),Constant.SHARED_PREFERENCES, Constant.SP_KEY_SPLASH_URL,url);
                                return Observable.just(url);
                            }
                            return null;
                        }
                    });
                }
                return null;
            }
        });
    }

    @Override
    public Observable<List<GankAndroidAndiOSEntities>> getAndroid(int rows, int pageNum) {
        return gankIoRepository.getAndroidList(rows,pageNum).flatMap(new BaseGankIoFunc<List<GankAndroidAndiOSEntities>>());
    }

    @Override
    public Observable<List<GankAndroidAndiOSEntities>> getIos(int rows, int pageNum) {
        return  gankIoRepository.getIosList(rows,pageNum).flatMap(new BaseGankIoFunc<List<GankAndroidAndiOSEntities>>());
    }

}
