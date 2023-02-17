package com.example.concurrency.example.Immutable;

import com.example.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/15 22:41
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    public static final Integer a = 1;
    public static final String b = "2";
    public static final Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    private void test(final int a) {
//        a = 1;
    }
}
