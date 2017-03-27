package com.jzhu.io.baselibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 *
 * @see #getWindowWidth(Context)
 * @see #getWindowHeight(Context)
 */
public class WindowUtil {

    private static DisplayMetrics sDisplayMetrics;
    private static int sStatusBarHeight;
    private static int sNavigationBarHeight;

    /**
     * get window width of pixels
     *
     * @param context
     *
     * @return
     */
    public static int getWindowWidth(Context context) {
        ensureMetrics(context);
        return sDisplayMetrics.widthPixels;
    }

    /**
     * get window height of pixels
     *
     * @param context
     *
     * @return
     */
    public static int getWindowHeight(Context context) {
        ensureMetrics(context);
        return sDisplayMetrics.heightPixels;
    }

    public static float getDensity(Context context){
        ensureMetrics(context);
        return sDisplayMetrics.density;
    }

    /**
     * Make sure DisplayMetrics is valid for Locker (screen height > screen width)
     * 
     * @param context
     */
    private static void ensureMetrics(Context context) {
        if (sDisplayMetrics == null || sDisplayMetrics.widthPixels > sDisplayMetrics.heightPixels) {
            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            sDisplayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(sDisplayMetrics);
        }
    }

//    public static int getStatusBarHeight(Context context) {
//        if (sStatusBarHeight <= 0) {
//            final Resources resources = context.getResources();
//            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//            if (resourceId <= 0) {
//                resourceId = R.dimen.status_bar_height;
//            }
//            sStatusBarHeight = resources.getDimensionPixelSize(resourceId);
//        }
//        return sStatusBarHeight;
//    }
//
//    public static int getNavigationBarHeight(Context context) {
//        if (sNavigationBarHeight <= 0) {
//            final Resources resources = context.getResources();
//            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//            if (resourceId <= 0) {
//                resourceId = R.dimen.navigation_bar_height;
//            }
//            sNavigationBarHeight = resources.getDimensionPixelSize(resourceId);
//        }
//        return sNavigationBarHeight;
//    }
}
