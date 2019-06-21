package com.bc.concurrency.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger demo
 * <p>
 * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
 * 当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，
 * 直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，之后线程A和B继续运行。
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
