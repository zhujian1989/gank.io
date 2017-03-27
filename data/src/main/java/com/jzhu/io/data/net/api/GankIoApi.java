package com.jzhu.io.data.net.api;

import com.jzhu.io.data.BaseGankIoResp;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.data.entities.GankFLEntities;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import java.util.List;

public interface GankIoApi {

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * @param rows
     * @param pageNum
     * @return
     */
    @GET("福利/{rows}/{pageNum}")
    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlList(@Path("rows") int rows, @Path("pageNum") int pageNum);

    @GET("Android/{rows}/{pageNum}")
    Observable<BaseGankIoResp<List<GankAndroidAndiOSEntities>>> getAndroidList(@Path("rows") int rows, @Path("pageNum") int pageNum);

    @GET("iOS/{rows}/{pageNum}")
    Observable<BaseGankIoResp<List<GankAndroidAndiOSEntities>>> getiOSList(@Path("rows") int rows, @Path("pageNum") int pageNum);

    /**
     *随机数据：http://gank.io/api/random/data/分类/个数
     *数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     *个数： 数字，大于0
     * @param num
     * @return
     */
    @GET("http://gank.io/api/random/data/福利/{num}")
    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlGrils(@Path("num") int num);
}
