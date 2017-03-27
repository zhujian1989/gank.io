package com.jzhu.io.data.net.fastjson;

import com.alibaba.fastjson.JSON;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Created by garrett on 3/24/16.
 */
public class FastJsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {

    private Type type;
    private Charset charset;

    public FastJsonResponseBodyConverter() {
    }

    public FastJsonResponseBodyConverter(Type type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return JSON.parseObject(value.string(), type);
        } finally {
            value.close();
        }
    }
}
