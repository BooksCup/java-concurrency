package com.bc.concurrency.concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏 demo
 * 让一组线程到达一个屏障是被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行
 *
 * @author zhou
 */
public class CyclicBarrierDemo {
    Random random = new Random();

    public void meeting(CyclicBarrier barrier) {
        try {
            Thread.sleep(random.nextInt(4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会...");

        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();

        CyclicBarrier barrier = new CyclicBarrier(10, () -> System.out.println("好！开始开会..."));
        for (int i = 0; i < 10; i++) {
            new Thread(() -> cyclicBarrierDemo.meeting(barrier)).start();
        }

        // 监控线程
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("等待的线程数: " + barrier.getNumberWaiting());
                System.out.println("is broken: " + barrier.isBroken());
            }
        }).start();
    }

}
