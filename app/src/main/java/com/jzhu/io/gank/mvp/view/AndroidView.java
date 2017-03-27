package com.jzhu.io.gank.mvp.view;

import com.jzhu.io.baselibrary.BaseView;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;

import java.util.List;

/**
 * Created by jzhu on 2016/11/22.
 */

public interface AndroidView extends BaseView {
    void getList(List<GankAndroidAndiOSEntities> list);

}
