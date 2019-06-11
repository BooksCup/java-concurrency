package com.bc.concurrency.test.lock;

import com.bc.concurrency.lock.ReentrantReadWriteLockDemo;

/**
 * 测试读写锁
 *
 * @author zhou
 */
public class ReentrantReadWriteLockDemoTest {
    /**
     * 测试读锁
     * 读读不互斥
     */
    public static void testReadLock() {
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        demo.set("key1", "value1");
        new Thread(() -> System.out.println(demo.get("key1"))).start();
        new Thread(() -> System.out.println(demo.get("key1"))).start();
        new Thread(() -> System.out.println(demo.get("key1"))).start();
    }

    /**
     * 测试写锁
     * 读写互斥
     */
    public static void testWriteLock() {
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        new Thread(() -> demo.set("key1", "value1")).start();
        new Thread(() -> demo.set("key2", "value2")).start();
        new Thread(() -> System.out.println(demo.get("key1"))).start();
    }

    public static void main(String[] args) {
//        testReadLock();
        testWriteLock();
    }
}
