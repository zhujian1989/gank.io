package com.jzhu.io.baselibrary.utils;

import java.util.List;

public class ObjectUtils {

	public static boolean isEmpty(Object object) {
		if (object == null || object.equals("") || object.equals("null")) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object object) {
		if (object == null || object.equals("") || object.equals("null")) {
			return false;
		}
		return true;
	}

	public static boolean isListEmpty(List list) {
		if ( null == list || list.isEmpty()) {
			return true;
		}
		return false;
	}

}
