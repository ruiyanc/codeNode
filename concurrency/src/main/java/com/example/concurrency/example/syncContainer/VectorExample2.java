package com.example.concurrency.example.syncContainer;

import com.example.concurrency.annoations.NotThreadSafe;
import com.example.concurrency.annoations.ThreadSafe;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/9 22:30
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector<>();

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < vector.size(); i++) {
            vector.add(i);
        }

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < vector.size(); i++) {
                vector.remove(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < vector.size(); i++) {
                vector.get(i);
            }
        });

        thread1.start();
        thread2.start();
    }

}
