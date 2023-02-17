package com.example.concurrency.example.atomic;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/9 22:30
 */
@Slf4j
@ThreadSafe
public class AtomicExample2 {
    private static int threadTotal = 200;
    private static int clientTotal = 5000;
    private static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
//        ThreadPoolExecutor exec = new ThreadPoolExecutor(1000, 5000, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

//        ExecutorService executor = Executors.newFixedThreadPool(400);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
        log.info("count:{}", count.get());
    }

    private static void add() {
        count.incrementAndGet();
    }
}
