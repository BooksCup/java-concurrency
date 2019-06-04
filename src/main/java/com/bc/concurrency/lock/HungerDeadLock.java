package com.bc.concurrency.lock;

import java.util.concurrent.*;

/**
 * 线程饥饿死锁
 *
 * @author zhou
 */
public class HungerDeadLock {

    ExecutorService exec = Executors.newSingleThreadExecutor();

    /**
     * 模拟页面加载的例子
     *
     * 产生死锁分析：
     * RenderPageTask任务中有2个子任务分别是“加载页眉”和“加载页脚”。
     * 当提交RenderPageTask任务时，实际上是向线程池中添加了3个任务，
     * 但是由于线程池是单一线程池，同时只会执行一个任务，2个子任务就会在阻塞在线程池中。
     * 而RenderPageTask任务由于得不到返回，也会一直堵塞，不会释放线程资源让子线程执行。这样就导致了线程饥饿死锁。
     *
     */
    class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header, footer;

            header = exec.submit(() -> {
                System.out.println("加载页眉");
                Thread.sleep(2 * 1000);
                return "页眉";

            });

            footer = exec.submit(() -> {
                System.out.println("加载页脚");
                Thread.sleep(2 * 1000);
                return "页脚";
            });

            System.out.println("渲染页面主体");
            return header.get() + footer.get();
        }
    }

    public static void main(String[] args) throws Exception {
        HungerDeadLock hungerDeadLock = new HungerDeadLock();
        Future<String> future = hungerDeadLock.exec.submit(hungerDeadLock.new RenderPageTask());
        String result = future.get();
        System.out.println("执行结果: " + result);
    }
}
