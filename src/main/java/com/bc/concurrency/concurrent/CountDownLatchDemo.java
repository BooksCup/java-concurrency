package com.bc.concurrency.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的例子
 * CountDownLatch也叫闭锁，使得主线程必须等待其他线程完成操作后再执行，可以想想tomcat的启动...
 * CountDownLatch内部维护一个计数器（父类的int state），主线程先执行await方法，
 * 如果此时计数器大于0，则阻塞等待。
 * 当一个线程完成任务后，计数器减1。
 * 直到计算器为0时，表示所有的线程已经完成任务，等待的主线程将被唤醒继续执行。
 *
 * @author zhou
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println("第一个子线程: " + Thread.currentThread().getName() + "开始执行.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一个子线程: " + Thread.currentThread().getName() + "执行完毕.");
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("第二个子线程: " + Thread.currentThread().getName() + "正在执行.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二个子线程: " + Thread.currentThread().getName() + "执行完毕.");
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("2个子线程已经执行完毕.");
        System.out.println("继续执行主线程.");
    }
}
