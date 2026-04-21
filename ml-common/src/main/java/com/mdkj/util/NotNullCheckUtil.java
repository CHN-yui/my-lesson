package com.mdkj.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 基于Hutool的非空校验工具类
 */
public class NotNullCheckUtil {

    /**
     * 判断对象非空，且如果是字符串则非空白字符串（排除null、""、"   "）
     * @param obj 待校验的对象
     * @return true：对象非空且非空白字符串；false：对象为空或为空白字符串
     */
    public static boolean checkNotNull(Object obj) {
        // 第一步：判断对象是否为null
        if (ObjectUtil.isNull(obj)) {
            return false;
        }

        // 第二步：如果是字符串类型，判断是否为空白字符串（null/空串/全空格）
        if (obj instanceof String) {
            return StrUtil.isNotBlank((String) obj);
        }

        // 非字符串类型且非null，直接返回true
        return true;
    }
}