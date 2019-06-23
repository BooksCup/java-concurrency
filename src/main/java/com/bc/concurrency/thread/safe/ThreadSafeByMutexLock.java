package com.bc.concurrency.thread.safe;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过互斥锁实现线程安全
 *
 * @author zhou
 */
public class ThreadSafeByMutexLock extends Thread {

    private int count = 5;

    ReentrantLock lock = new ReentrantLock();

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
        ThreadSafeByMutexLock threadSafeByMutexLock = new ThreadSafeByMutexLock();
        Thread t1 = new Thread(threadSafeByMutexLock, "t1");
        Thread t2 = new Thread(threadSafeByMutexLock, "t2");
        Thread t3 = new Thread(threadSafeByMutexLock, "t3");
        Thread t4 = new Thread(threadSafeByMutexLock, "t4");
        Thread t5 = new Thread(threadSafeByMutexLock, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
