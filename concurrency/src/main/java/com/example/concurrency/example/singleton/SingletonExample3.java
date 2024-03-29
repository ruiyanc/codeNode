package com.example.concurrency.example.singleton;

import com.example.concurrency.annoations.NotRecommend;
import com.example.concurrency.annoations.ThreadSafe;

/**
 * @author xurui
 * @description 懒汉模式
 * 单例实例在第一次使用时进行创建
 * @date 2023/2/15 21:46
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 私有构造函数
     */
    private SingletonExample3() {

    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
