package com.example.concurrency.example.singleton;

import com.example.concurrency.annoations.NotThreadSafe;

/**
 * @author xurui
 * @description 懒汉模式
 * 单例实例在第一次使用时进行创建
 * @date 2023/2/15 21:46
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有构造函数
     */
    private SingletonExample1() {

    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
