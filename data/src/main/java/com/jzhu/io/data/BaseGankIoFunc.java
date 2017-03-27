package com.jzhu.io.data;

import com.jzhu.io.data.exception.BusinessGankIoException;
import rx.Observable;
import rx.functions.Func1;

public class BaseGankIoFunc<T> implements Func1<BaseGankIoResp<T>, Observable<T>> {
    @Override
    public Observable<T> call(BaseGankIoResp<T> resp) {
        if (resp.getError()) {
            return Observable.error(new BusinessGankIoException());
        }
        return Observable.just(resp.getResults());
    }
}
