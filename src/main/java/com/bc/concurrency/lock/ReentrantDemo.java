package com.bc.concurrency.lock;

/**
 * 证明synchronize是可重入锁的一个小例子
 * @author zhou
 */
public class ReentrantDemo {
    public synchronized void firstMethod(){
        System.out.println("firstMethod");
        secondMethod();
    }

    public synchronized void secondMethod(){
        System.out.println("secondMethod");
    }

    public static void main(String[] args){
        ReentrantDemo reentrantDemo = new ReentrantDemo();
        new Thread(() -> reentrantDemo.firstMethod()).start();
    }
}
