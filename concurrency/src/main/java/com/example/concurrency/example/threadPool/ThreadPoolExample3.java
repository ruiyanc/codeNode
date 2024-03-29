package com.example.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/24 22:43
 */
@Slf4j
public class ThreadPoolExample3 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                log.info("task:{}", index);
            });
        }

        executorService.shutdown();
    }
}
