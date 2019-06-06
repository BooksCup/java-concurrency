package com.bc.concurrency.test.lock;

import com.bc.concurrency.lock.MyReentrantLock;

import java.util.concurrent.locks.Lock;

/**
 * 测试自己实现锁
 * MyReentrantLock
 *
 * @author zhou
 */
public class MyLockTest {

    private Lock lock = new MyReentrantLock();

    private int value;

    private int getNext() {
        lock.lock();
        value++;
        lock.unlock();
        return value;
    }

    // 用于测试可重入的method===begin
    private void methodFirst() {
        lock.lock();
        System.out.println("methodFirst");
        methodSecond();
        lock.unlock();
    }

    private void methodSecond() {
        lock.lock();
        System.out.println("methodSecond");
        lock.unlock();
    }
    // 用于测试可重入的method===end


    /**
     * 测试锁是否有用
     */
    private static void testLockUseful() {
        MyLockTest t = new MyLockTest();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(t.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 测试锁是否可重用
     */
    private static void testLockIsReentrant() {
        MyLockTest t = new MyLockTest();
        new Thread(() -> t.methodFirst()).start();
    }

    public static void main(String[] args) {
        // 测试锁是否有用
//        testLockUseful();
        // 测试锁是否可重用
        testLockIsReentrant();
    }

}
