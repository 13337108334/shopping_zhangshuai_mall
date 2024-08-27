package com.shanzhu.em.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author zhangDaYe
 * @date 2024/08/27
 */
public class BeanCopyUtil extends BeanUtils {

    /**
     * 集合拷贝
     * 用法示例：List<targetModel> targetList  = BeanCopyUtil.copyListProperties(sourceList, targetModel::new);
     * @param sources: 数据源集合
     * @param target: 目标类::new(eg: UserVO::new)
     * @return 返回集合
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 集合拷贝并修改
     * 用法示例：            List<EntryInfo> entryInfos = BeanCopyUtil.copyListProperties(param.getEntryInfos(),
     *                     EntryInfo::new,
     *                     (s, t) -> t.setDeliveryDate(ITGDateUtil.parseDate(s.getDeliveryDate(), ITGDateUtil.DEFAULT_TIME_FORMAT)));
     * @param sources 数据源集合
     * @param target 目标类::new(eg: UserVO::new)
     * @param callBack 回调函数
     * @param <S> 数据源集合类型
     * @param <T> 目标类型
     * @return 返回集合
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        if(CollectionUtils.isEmpty(sources)){
            List<T> tList = new ArrayList<>();
            return tList;
        }

        List<T> list = new ArrayList<>(sources.size());
        sources.stream()
                .filter(Objects::nonNull)
                .forEach(source ->{
                    T t = target.get();
                    copyProperties(source, t);
                    list.add(t);
                    if (callBack != null) {
                        // 回调逻辑
                        callBack.callBack(source, t);
                    }
                });

        return list;
    }
}
