package com.jzhu.io.data.net.interceptor;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by zhujian on 2017/3/29.
 */

public class TimeoutIntercepter implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        try {
            response = chain.proceed(chain.request());
        }
        catch (SocketTimeoutException exception) {
            exception.printStackTrace();
        }

        return response;
    }
}
