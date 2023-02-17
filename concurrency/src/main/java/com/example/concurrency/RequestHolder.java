package com.example.concurrency;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/16 22:04
 */
public class RequestHolder {
    public static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
