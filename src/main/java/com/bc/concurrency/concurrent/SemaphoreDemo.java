package com.bc.concurrency.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 信号量 demo
 * Semaphore类是一个计数信号量，必须由获取它的线程释放，
 * 通常用于限制可以访问某些资源（物理或逻辑的）线程数目。
 *
 * @author zhou
 */
public class SemaphoreDemo {

    public void method(Semaphore semaphore) {
        try {
            // 从信号量中获得许可证
            semaphore.acquire(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " is run ...");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 释放许可证至信号量中
        semaphore.release(5);
    }

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        // 初始化10个许可证
        Semaphore semaphore = new Semaphore(10);
        while (true) {
            new Thread(() -> {
                semaphoreDemo.method(semaphore);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
