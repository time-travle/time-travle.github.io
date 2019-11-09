/**
 * Project Name: ershuaizhang.github.io
 * File Name: ValidateUtils
 * Package Name: com.joven.base.common
 * Date: 2019/11/8 22:38
 * Copyright (c) 2019,All Rights Reserved.
 */
package com.joven.base.common;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CreateBy Administrator
 * Date: 2019/11/8 22:38
 * Version: 
 * Remark: 
 */
public class ValidateUtil{
    private ValidateUtil(){}
    /**
     * string 为null/""/或者只有空格都是空
     */
    public static boolean isEmptyString(String string) {
        // string is "" or null
        if ("".equals(string) || null == string) {
            return true;
        }
        // string is combine all with blank
        if (string.trim().length()<=0) {
            return true;
        }
        return false;
    }
    /**
     * list判断是不是空的集合
     * @param list
     * @return
     */
    public static boolean isEmptyCollection(List<?> list) {
        // the list is null or undefined
        if (null==list || list.isEmpty()||0 == list.size()) {
            return true;
        }
        return false;
    }


    /**
     * Object  判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param
     * @return
     */
    public static boolean isNullOrEmptyObject(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection<?>) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map<?, ?>) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmptyObject(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

}
