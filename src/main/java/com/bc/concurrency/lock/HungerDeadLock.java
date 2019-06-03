package com.bc.concurrency.lock;

import java.util.concurrent.*;

/**
 * 线程饥饿死锁
 *
 * @author zhou
 */
public class HungerDeadLock {

    ExecutorService exec = Executors.newSingleThreadExecutor();

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
