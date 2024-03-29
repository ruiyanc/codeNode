package com.example.concurrency.example.lock;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/13 22:38
 */
@Slf4j
@ThreadSafe
public class LockExample4 {
    class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        void move(double deltaX, double deltaY) { // an exclusively locked method
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        //乐观读锁案例
        double distanceFromOrigin() { // A read-only method
            long stamp = sl.tryOptimisticRead();  // 获得一个乐观读锁
            double currentX = x, currentY = y;  // 将两个字段读入本地局部变量
            if (!sl.validate(stamp)) {  // 检查发出乐观读锁后同时是否有其他写锁发生
                stamp = sl.readLock();  // 如果没有我们再次获取一个读悲观锁
                try {
                    currentX = x;  // 将两个字段读入本地局部变量
                    currentY = y;  // 将两个字段读入本地局部变量
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

        // 悲观读锁案例
        void moveIfAtOrigin(double newX, double newY) { // upgrade
            // Could instead start with optimistic, not read mode
            long stamp = sl.readLock();
            try {
                while (x == 0.0 && y == 0.0) { // 循环，检查当前状态是否符合
                    long ws = sl.tryConvertToWriteLock(stamp); // 将读锁转为写锁
                    if (ws != 0L) { // 确认转为写锁是否成功
                        stamp = ws; // 如果成功替换票据
                        x = newX; // 进行状态改变
                        y = newY; // 进行状态改变
                        break;
                    }
                    else {  // 如果不能成功转换为写锁
                        sl.unlockRead(stamp); // 我们显式释放读锁
                        stamp = sl.writeLock(); // 显式直接进行写锁，然后再通过循环再试
                    }
                }
            } finally {
                sl.unlock(stamp);
            }
        }
    }

}
