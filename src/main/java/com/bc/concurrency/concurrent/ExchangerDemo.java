package com.bc.concurrency.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger demo
 *
 * @author zhou
 */
public class ExchangerDemo {
    final static int LOOP_COUNT = 20;

    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            for (int i = 0; i < LOOP_COUNT; i++) {
                final String origin = "A(" + i + ")";
                try {
                    System.out.println(origin + " begin exchange");
                    Thread.sleep(10000);
                    String result = exchanger.exchange(origin);
                    System.out.println(origin + " print: " + result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(() -> {
            for (int i = 0; i < LOOP_COUNT; i++) {
                final String origin = "B(" + i + ")";
                try {
                    System.out.println(origin + " begin exchange");
                    Thread.sleep(5000);
                    String result = exchanger.exchange(origin);
                    System.out.println(origin + " print: " + result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
