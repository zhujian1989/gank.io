package com.jzhu.io.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.jzhu.io.data.common.Constant;

/**
 * Created by jzhu on 2016/8/17.
 */
public class PreferenceUtil {
	public static void clear(Context context, String strPref){
		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}
		settings.edit().clear().commit();
		PackageInfo pInfo = null;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		}
		catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		String version = pInfo.versionName;
		PreferenceUtil.setBoolean(context, Constant.SHARED_PREFERENCES, version, true);
	}

	public static void clearPrefs(Context context, String strPref, String strKey) {
		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		settings.edit().remove(strKey).commit();
	}

	public static String getString(Context context, String strPref,
			String strKey, String strDefValue) {
		if (null == context) {
			return strDefValue;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return strDefValue;
		}

		return settings.getString(strKey, strDefValue);
	}

	public static void setString(Context context, String strPref,
			String strKey, String strValue) {
		if (null == context) {
			return;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return;
		}

		SharedPreferences.Editor editor = settings.edit();
		editor.putString(strKey, strValue);
		editor.commit();
	}

	public static int getInt(Context context, String strPref, String strKey,
			int nDefValue) {
		if (null == context) {
			return nDefValue;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return nDefValue;
		}

		return settings.getInt(strKey, nDefValue);
	}

	public static void setInt(Context context, String strPref, String strKey,
			int nValue) {
		if (null == context) {
			return;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return;
		}

		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(strKey, nValue);
		editor.commit();
	}

	public static boolean getBoolean(Context context, String strPref,
			String strKey, boolean bDefValue) {
		if (null == context) {
			return bDefValue;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return bDefValue;
		}

		return settings.getBoolean(strKey, bDefValue);
	}

	public static void setBoolean(Context context, String strPref,
			String strKey, boolean bValue) {
		if (null == context) {
			return;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return;
		}

		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(strKey, bValue);
		editor.commit();
	}

	public static long getLong(Context context, String strPref, String strKey,
			long nDefValue) {
		if (null == context) {
			return nDefValue;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return nDefValue;
		}

		return settings.getLong(strKey, nDefValue);
	}

	public static void setLong(Context context, String strPref, String strKey,
			long nValue) {
		if (null == context) {
			return;
		}

		SharedPreferences settings = null;
		if (TextUtils.isEmpty(strPref))
			settings = PreferenceManager.getDefaultSharedPreferences(context);
		else {
			settings = context.getSharedPreferences(strPref, 0);
		}

		if (null == settings) {
			return;
		}

		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(strKey, nValue);
		editor.commit();
	}
}
