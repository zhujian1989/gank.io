package com.jzhu.io.data.net;

import com.jzhu.io.data.common.Constant;
import com.jzhu.io.data.net.fastjson.FastJsonConverterFactory;
import com.jzhu.io.data.net.interceptor.HttpInterceptor;
import com.jzhu.io.data.net.interceptor.TimeoutIntercepter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitFactory {

    public static String BASE_URL = Constant.SERVER_ADDRESS;

    private static volatile RetrofitFactory sInstance;

    private Retrofit retrofit;

    private RetrofitFactory() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)      //访问主机地址
                    .addConverterFactory(FastJsonConverterFactory.create())  //解析方式
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(genericClient())
                    .build();
    }


    public static RetrofitFactory getInstance() {
        if (null == sInstance) {
            synchronized (RetrofitFactory.class) {
                if (null == sInstance) {
                    sInstance = new RetrofitFactory();
                }
            }
        }
        return sInstance;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    private static HttpLoggingInterceptor initLoggingInterceptor() {
        HttpLoggingInterceptor
                httpLoggingInterceptor =
                new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(initLoggingInterceptor())
                .addInterceptor(new HttpInterceptor())
                .addInterceptor(new TimeoutIntercepter())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }
}


