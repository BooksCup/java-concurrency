package com.bc.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 *
 * @author zhou
 */
public class FairLock {

    private static final Integer THREAD_NUM = 10;

    /**
     * 公平锁
     */
    ReentrantLock lock = new ReentrantLock(true);

    /**
     * 非公平锁
     */
//    ReentrantLock lock = new ReentrantLock(false);

    public void checkFair() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动！");
            fairLock.checkFair();
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < THREAD_NUM; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            threadArray[i].start();
        }
    }
}
