package com.jzhu.io.data.net.interceptor;

import okhttp3.*;

import java.io.IOException;
import java.net.URL;

/**
 * Created by zhujian on 2017/3/29.
 */

public class FakeInterceptor implements Interceptor{


    private String curUrl;
    private String mockJson;

    public FakeInterceptor(String url, String respJson){
        curUrl = url;
        mockJson = respJson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        URL url  = chain.request().url().url();
        if(url.getPath().equals(curUrl)){
            response = new Response.Builder()
                    .code(200)
                    .message(mockJson)
                    .protocol(Protocol.HTTP_1_0)
                    .request(chain.request())
                    .body(ResponseBody.create(MediaType.parse("application/json"), mockJson.getBytes()))
                    .addHeader("content-type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build();

            return response;
        }else{
            return chain.proceed(chain.request());
        }
    }

}
