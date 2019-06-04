package com.bc.concurrency.lock;

//import java.util.Map;

import java.util.Random;

/**
 * 自旋锁的例子
 *
 * @author zhou
 */
public class SpinLockDemo {
    public static void main(String[] args) {
        TestThread t1 = new TestThread();
        TestThread t2 = new TestThread();
        TestThread t3 = new TestThread();
        TestThread t4 = new TestThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        /**
         * idea这边是2,直接java命令或者eclipse是1
         * 原因分析:可以通过下面注释掉的代码打印当前线程
         * 会有如下几个线程:
         * Thread[Monitor Ctrl-Break,5,main]
         * Thread[Attach Listener,5,system]
         * Thread[main,5,main]
         * Thread[Signal Dispatcher,9,system]
         * Thread[Finalizer,8,system]
         * Thread[Reference Handler,10,system]
         * 可以看到非系统线程共有2个,main线程容易理解,多了一个Monitor Ctrl-Break
         * 通过查看intellij-community源码:
         * https://github.com/JetBrains/intellij-community/blob/master/java/java-runtime/src/com/intellij/rt/execution/application/AppMainV2.java
         * 发现IntelliJ IDEA执行用户代码的时候，实际是通过反射方式去调用，而与此同时会创建一个Monitor Ctrl-Break 用于监控目的。
         */
        while (Thread.activeCount() != 2) {
            // 线程挂起
            // CPU空跑
        }
        System.out.println("所有线程执行完毕了...");

//        while (true) {
//            System.out.println("activeCount: " + Thread.activeCount());
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
//            for (Map.Entry entry : maps.entrySet()) {
//                System.out.println(entry.getKey());
//            }
//
//        }
    }

}

class TestThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行...");
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束...");
    }
}
