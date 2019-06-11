package com.bc.concurrency.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 关于读写锁的小例子
 *
 * @author zhou
 */
public class ReentrantReadWriteLockDemo {
    private Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    /**
     * 获取map的值
     * 测试读写锁的读锁
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        // 读锁
        rwl.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " 读操作正在执行...");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            System.out.println(Thread.currentThread().getName() + " 读操作执行完毕...");
            rwl.readLock().unlock();
        }
    }

    /**
     * set值至map
     * 测试读写锁的写锁
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        // 写锁
        rwl.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " 写操作正在执行...");
        try {
            Thread.sleep(3000);
            map.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 写操作执行完毕...");
            rwl.writeLock().unlock();
        }
    }
}
