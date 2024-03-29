package com.example.concurrency.example.singleton;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * @author xurui
 * @description 饿汉模式
 * 单例实例在类装载时创建
 * @date 2023/2/15 21:46
 */
@ThreadSafe
public class SingletonExample6 {

    /**
     * 私有构造函数
     */
    private SingletonExample6() {

    }

    //单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
