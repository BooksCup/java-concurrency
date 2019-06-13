package com.bc.concurrency.communication;

/**
 * 加塞示例
 *
 * @author zhou
 */
public class JoinDemo {
    public static void main(String[] args) {
        JoinDemo joinDemo = new JoinDemo();

        Thread joinThread = new Thread(() -> joinDemo.joinMethod());

        new Thread(() -> joinDemo.firstMethod(joinThread)).start();
    }

    public void firstMethod(Thread joinThread) {
        System.out.println("firstMethod方法执行开始 ...");
        joinThread.start();
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("firstMethod方法执行完毕 ...");
    }

    public void joinMethod() {
        System.out.println("加塞方法执行开始 ...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("加塞方法执行完毕 ...");
    }
}
