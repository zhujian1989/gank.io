package com.jzhu.io.data.net.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zhujian on 2017/3/29.
 */

public class HttpInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                               .newBuilder()
                               .addHeader("Content-Type",
                                          "application/json")
                               .addHeader("charset", "UTF-8")
                               .build();
        return chain.proceed(request);
    }
}
