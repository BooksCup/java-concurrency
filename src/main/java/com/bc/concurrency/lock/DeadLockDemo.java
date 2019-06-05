package com.bc.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于java死锁的小例子
 * 运行jconsole可观察到线程Thread-0和Thread-1都进入死锁状态
 * @author zhou
 */
public class DeadLockDemo {
    private Object o1 = new Object();
    private Object o2 = new Object();

    public void methodFirst() {
        synchronized (o1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2) {
                System.out.println("methodFirst");
            }
        }
    }

    public void methodSecond() {
        synchronized (o2) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1) {
                System.out.println("methodSecond");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        new Thread(() -> deadLockDemo.methodFirst()).start();
        new Thread(() -> deadLockDemo.methodSecond()).start();
    }
}
