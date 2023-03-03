package com.example.concurrency.example.count;

import com.example.concurrency.annoations.NotThreadSafe;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/13 22:38
 */
@Slf4j
@NotThreadSafe
public class CountExample2 {
    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    @SneakyThrows
    public static void main(String[] args) {
//        ExecutorService exec = Executors.newCachedThreadPool();
        ThreadPoolExecutor exec = new ThreadPoolExecutor(200, 5000, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
//        ExecutorService executor = Executors.newFixedThreadPool(400);
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int index = 0; index < clientTotal; index++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
    }
}
