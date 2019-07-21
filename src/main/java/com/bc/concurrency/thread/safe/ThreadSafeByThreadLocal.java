package com.bc.concurrency.thread.safe;

import java.util.Random;

/**
 * 通过threadLocal解决线程安全问题
 *
 * @author zhou
 */
public class ThreadSafeByThreadLocal {
    public static class RandomRunnable implements Runnable {
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            int randomNum = new Random().nextInt(1000);
            threadLocal.set(randomNum);
            System.out.println(Thread.currentThread().getName() + " set random num: " + randomNum);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " get random num: " + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        RandomRunnable randomRunnable = new RandomRunnable();
        Thread t1 = new Thread(randomRunnable, "t1");
        Thread t2 = new Thread(randomRunnable, "t2");
        t1.start();
        t2.start();
    }
}
