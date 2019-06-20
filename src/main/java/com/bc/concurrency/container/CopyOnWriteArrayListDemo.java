package com.bc.concurrency.container;

//import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ArrayList的线程安全的变体
 * 当容器内数据、数量等没有发生变化的时候，多线程并发读所读到的数据都是唯一、一致、安全的
 * 如果此时有线程往容器中添加新的数据，此时CopyOnWriteArrayList会先copy一个副本，
 * 往新容器中添加这个新的数据，添加完成后把新的容器地址赋值给了之前那个旧的的容器地址
 * 添加这个数据的期间，其他线程要是在读，读到是旧容器的数据
 *
 * @author zhou
 */
public class CopyOnWriteArrayListDemo {
    private static final int THREAD_POOL_MAX_NUM = 10;

    /**
     * 多线程同时对List容器进行读写，如果直接使用ArrayList则会报错
     */
    //    private List<String> mList = new ArrayList<>();
    private List<String> mList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new CopyOnWriteArrayListDemo().start();
    }

    private void initData() {
        for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
            this.mList.add("...... Line " + (i + 1) + " ......");
        }
    }

    private void start() {
        initData();
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_MAX_NUM * 2);
        for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
            service.execute(new ReaderThread(this.mList));
            service.execute(new WriterThread(this.mList, i));
        }
        service.shutdown();
    }

    private class ReaderThread implements Runnable {

        private List<String> mList;

        public ReaderThread(List<String> List) {
            this.mList = List;
        }

        @Override
        public void run() {
            if (null != this.mList) {
                for (String s : this.mList) {
                    System.out.println(Thread.currentThread().getName() + " : " + s);
                }
            }
        }
    }

    private class WriterThread implements Runnable {

        private List<String> mList;

        private int mIndex;

        public WriterThread(List<String> List, int index) {
            this.mList = List;
            this.mIndex = index;
        }

        @Override
        public void run() {
            if (null != this.mList) {
                this.mList.add("===> add: " + this.mIndex);
            }
        }
    }
}
