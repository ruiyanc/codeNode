package com.example.concurrency.example.aqs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/20 22:07
 */
@Slf4j
public class SemaphoneExample2 {
    public static final int threadCount = 200;

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(50);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    //获取多个许可
                    semaphore.acquire(3);
                    test(threadNum);
                    //释放多个许可
                    semaphore.release(3);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
