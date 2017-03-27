package com.jzhu.io.baselibrary.utils.delay;

/**
 * Created by thl on 2016/11/17.
 */

public abstract class OnDelayListenerAdapter implements DelayUtils.OnDelayListener {

    @Override
    public abstract void onDelayFinish();


    @Override
    public void onDelaying() {

    }
}
