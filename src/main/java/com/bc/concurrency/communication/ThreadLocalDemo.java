package com.bc.concurrency.communication;

/**
 * ThreadLocal的例子
 *
 * @author zhou
 */
public class ThreadLocalDemo {
    private ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> new Integer(0));

    public Integer getNext() {
        Integer value = count.get();
        value++;
        count.set(value);
        return value;
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + threadLocalDemo.getNext());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + threadLocalDemo.getNext());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
