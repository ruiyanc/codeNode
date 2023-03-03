package com.example.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/24 22:43
 */
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

//        executorService.schedule(() -> {
//            log.warn("schedule run");
//        }, 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(() -> {
            log.warn("schedule run");
        }, 1, 3, TimeUnit.SECONDS);
//        executorService.shutdown();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000);
    }
}
