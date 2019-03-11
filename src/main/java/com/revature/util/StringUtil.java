package com.revature.util;

import com.revature.exception.StringEmptyException;

public final class StringUtil {
	
	/* It assumes that the string comes in upper case */
	public static String toCamelCase(String str) throws StringEmptyException {
		if(str == null) {
			throw new StringEmptyException("Cannot camel case an empty string.");
		}
		else {
			return str.charAt(0) + str.substring(1).toLowerCase();
		}
	}
}
