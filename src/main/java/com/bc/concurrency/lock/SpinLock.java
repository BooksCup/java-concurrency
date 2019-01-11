package com.bc.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁
 *
 * @author zhou
 */
public class SpinLock implements Lock {

    /**
     * java中原子（CAS）操作
     * 持有自旋锁的线程对象
     */
    AtomicReference<Thread> owner = new AtomicReference<>();

    private int count = 0;

    @Override
    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            // 如果当前线程已经获取到了锁,计数器+1,然后返回
            count++;
            return;
        }
        // 如果没获取到锁,通过CAS自旋
        while (!owner.compareAndSet(null, t)) {

        }
    }

    @Override
    public void unlock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            if (count > 0) {
                // 如果大于0,表示当前线程多次获取到了锁,释放锁只是通过计数器-1来模拟
                count--;
            } else {
                // 如果count == 0,可以将锁释放,这样就能保证获取锁的次数与释放锁的次数是一致的
                owner.compareAndSet(t, null);
            }
        }
    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
