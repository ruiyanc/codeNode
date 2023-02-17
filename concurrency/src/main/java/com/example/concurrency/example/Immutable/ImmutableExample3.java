package com.example.concurrency.example.Immutable;

import com.example.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/15 22:41
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    public static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
    public static final ImmutableSet<Integer> set = ImmutableSet.copyOf(list);
    public static final ImmutableMap<Integer, Integer> map = ImmutableMap.of(1,2,3,4);
    public static final ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer,Integer>builder()
            .put(1,2).put(3,4).put(5,6).build();

    public static void main(String[] args) {
        //不允许的操作
        list.add(4);
        set.add(4);
        map.put(1, 3);
    }
}
