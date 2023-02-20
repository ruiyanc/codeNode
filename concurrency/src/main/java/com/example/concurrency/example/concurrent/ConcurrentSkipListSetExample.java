package com.example.concurrency.example.concurrent;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/9 22:30
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {
    private static int threadTotal = 200;
    private static int clientTotal = 5000;
    private static Set<Integer> list = new ConcurrentSkipListSet<>();

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

//        ExecutorService executor = Executors.newFixedThreadPool(400);
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int index = 0; index < clientTotal; index++) {
            final int count = index;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("size:{}", list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
