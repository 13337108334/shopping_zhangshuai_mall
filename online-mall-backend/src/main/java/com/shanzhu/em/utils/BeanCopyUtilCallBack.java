package com.shanzhu.em.utils;

/**
 * @author zhangDaYe
 * @date 2024/08/27
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S,T> {
    /**
     * 定义默认回调方法
     * @param t 目标
     * @param s 来源
     */
    void callBack(S t, T s);
}
