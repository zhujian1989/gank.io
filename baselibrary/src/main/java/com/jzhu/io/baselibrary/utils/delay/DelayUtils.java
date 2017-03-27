package com.jzhu.io.baselibrary.utils.delay;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by thl on 2016/11/17.
 */

public class DelayUtils {

    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isDelaying;

    public DelayUtils() {
        mTimer = new Timer();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isDelaying = false;
            if (mOnDelayListener != null) mOnDelayListener.onDelayFinish();
        }
    };

    public void delay(long delay, OnDelayListener listener) {

        this.mOnDelayListener = listener;
        isDelaying = true;
        if (mOnDelayListener != null) mOnDelayListener.onDelaying();
        mTimer.schedule(mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }, delay);
    }

    public void cancel() {
        if (mTimer != null)
            mTimer.cancel();
        mTimerTask = null;
        isDelaying = false;
    }

    public boolean isDelaying() {
        return isDelaying;
    }

    private OnDelayListener mOnDelayListener;

    public void setOnDelayListener(OnDelayListener onDelayListener) {
        mOnDelayListener = onDelayListener;
    }

    public interface OnDelayListener {
        void onDelayFinish();

        void onDelaying();
    }

}
