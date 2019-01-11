package com.bc.concurrency.test.lock;

import com.bc.concurrency.lock.SpinLock;

public class SpinLockTest implements Runnable {

    private int count = 5;

    SpinLock lock = new SpinLock();

    @Override
    public void run() {
        try {
            lock.lock();
            count--;
            System.out.println(Thread.currentThread().getName() + " count: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SpinLockTest spinLockTest = new SpinLockTest();
        Thread t1 = new Thread(spinLockTest, "t1");
        Thread t2 = new Thread(spinLockTest, "t2");
        Thread t3 = new Thread(spinLockTest, "t3");
        Thread t4 = new Thread(spinLockTest, "t4");
        Thread t5 = new Thread(spinLockTest, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
