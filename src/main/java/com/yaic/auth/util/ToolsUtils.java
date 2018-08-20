package com.yaic.auth.util;

import java.util.List;

public class ToolsUtils {

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(List list) {
        if (list == null || list.size() < 1) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String[] items) {
        if (items == null || items.length < 1) {
            return true;
        }
        return false;
    }

    public static boolean notEmpty(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public static boolean notEmpty(List list) {
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean notEmpty(String[] items) {
        if (items != null && items.length > 0) {
            return true;
        }
        return false;
    }

}
