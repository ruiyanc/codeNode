package com.example.concurrency.example.singleton;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * @author xurui
 * @description 饿汉模式
 * 单例实例在类装载时创建
 * @date 2023/2/15 21:46
 */
@ThreadSafe
public class SingletonExample2 {

    /**
     * 私有构造函数
     */
    private SingletonExample2() {

    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
