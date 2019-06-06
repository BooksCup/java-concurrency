package com.bc.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现自己的可重入锁
 *
 * @author zhou
 */
public class MyReentrantLock implements Lock {
    private boolean isLocked = false;

    private Thread lockBy = null;
    private int lockCount = 0;

    @Override
    public synchronized void lock() {
        Thread current = Thread.currentThread();
        if (isLocked && current != lockBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockBy = current;
        lockCount++;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {
        if (lockBy != Thread.currentThread()) {
            return;
        }
        lockCount--;
        if (lockCount == 0) {
            isLocked = false;
            notify();
            lockBy = null;
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
