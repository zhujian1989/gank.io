package com.jzhu.io.gank.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class FloatingLayout extends RelativeLayout {

    private ViewDragHelper viewDragHelper;

    private boolean isCaptured;

    private View dragView;

    private int left;
    private int top;
    private boolean isFrist  = true;

    public FloatingLayout(Context context) {
        this(context, null);
    }

    public FloatingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new CustomCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = getChildAt(0);
    }

    private class CustomCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (getChildCount() > 0) {
                isCaptured = child == getChildAt(getChildCount() - 1);
                return isCaptured;
            }
            return false;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            int leftBound = getPaddingLeft();
            int rightBound = getWidth() - child.getWidth() - getPaddingRight();
            int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int topBound = getPaddingTop();
            int bottomBound = getHeight() - child.getHeight() - getPaddingBottom();
            int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return getMeasuredWidth() - child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight() - child.getMeasuredHeight();
        }


        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            int leftBound = getPaddingLeft();
            int rightBound = getWidth() - releasedChild.getWidth() - getPaddingRight();
            viewDragHelper.settleCapturedViewAt(releasedChild.getLeft() > (getWidth() - releasedChild.getWidth()) / 2f ? rightBound : leftBound, releasedChild.getTop());
            invalidate();
            left = releasedChild.getLeft() > (getWidth() - releasedChild.getWidth()) / 2f ? rightBound : leftBound;
            top = releasedChild.getTop();
            isCaptured = false;
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return isCaptured;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true))
            invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(isFrist){
            isFrist =false;
            left = dragView.getLeft();
            top = dragView.getTop();
        }
        dragView.layout(left, top, left + dragView.getMeasuredWidth(), top + dragView.getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
