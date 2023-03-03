package com.example.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xurui
 * @description 一个简单的死锁类
 * 当DeadLock的类的对象flag==1时，先锁定o1，睡眠500毫秒
 * 当dl1睡眠结束后需要锁定o2才能继续执行，而此时o2已被dl2锁定；
 * 当dl2睡眠结束后需要锁定o2才能继续执行，而此时o1已被dl1锁定；
 * dl1、dl2相互等待都需要得到对方锁定的资源才能继续执行，从而死锁
 *
 * @date 2023/2/24 23:44
 */
@Slf4j
public class DeadLock implements Runnable{

    public int flag = 1;
    //静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
//            排他性
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("1");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("0");
                }
            }
        }
    }


    public static void main(String[] args) {
        DeadLock dl1 = new DeadLock();
        DeadLock dl2 = new DeadLock();
        dl1.flag = 1;
        dl2.flag = 0;
        //两个都处于可执行状态，JVM线程调度先执行哪个线程是不确定的
        new Thread(dl1).start();
        new Thread(dl2).start();
    }
}
