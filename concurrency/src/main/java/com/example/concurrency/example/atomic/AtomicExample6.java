package com.example.concurrency.example.atomic;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/9 22:30
 */
@Slf4j
@ThreadSafe
public class AtomicExample6 {

    private static int threadTotal = 200;
    private static int clientTotal = 5000;
    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
//        ThreadPoolExecutor exec = new ThreadPoolExecutor(1000, 5000, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

//        ExecutorService executor = Executors.newFixedThreadPool(400);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
        log.info("count:{}", isHappened.get());
    }

    private static void test() {
        //只执行一次就返回
        if (isHappened.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}