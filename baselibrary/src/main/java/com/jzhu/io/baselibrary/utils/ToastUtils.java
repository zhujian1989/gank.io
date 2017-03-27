package com.jzhu.io.baselibrary.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

        public static boolean isShow = true;

        /**
         * 短时间显示Toast
         *
         * @param context
         * @param message
         */
        public static void showShortMessage(Context context, CharSequence message)
        {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

        /**
         * 短时间显示Toast
         *
         * @param context
         * @param message
         */
        public static void showShortMessage(Context context, int message)
        {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

        /**
         * 长时间显示Toast
         *
         * @param context
         * @param message
         */
        public static void showLongMessage(Context context, CharSequence message)
        {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

        /**
         * 长时间显示Toast
         *
         * @param context
         * @param message
         */
        public static void showLongMessage(Context context, int message)
        {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

        /**
         * 自定义显示Toast时间
         *
         * @param context
         * @param message
         * @param duration
         */
        public static void showMessage(Context context, CharSequence message, int duration)
        {
            if (isShow)
                Toast.makeText(context, message, duration).show();
        }

        /**
         * 自定义显示Toast时间
         *
         * @param context
         * @param message
         * @param duration
         */
        public static void showmessage(Context context, int message, int duration)
        {
            if (isShow)
                Toast.makeText(context, message, duration).show();
        }

}
