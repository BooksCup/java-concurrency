package com.bc.concurrency.thread.safe;

/**
 * 通过局部变量解决线程安全性问题
 *
 * @author zhou
 */
public class ThreadSafeByLocalVariables extends Thread {

    @Override
    public void run() {
        double[] scores = new double[]{1.0, 2.0, 3.0};
        double avgScore = avgScore(scores);
        System.out.println(Thread.currentThread().getName() + ", avgScore: " + avgScore);
    }

    public static void main(String[] args) {
        ThreadSafeByLocalVariables threadSafeByLocalVariables = new ThreadSafeByLocalVariables();
        Thread t1 = new Thread(threadSafeByLocalVariables, "t1");
        Thread t2 = new Thread(threadSafeByLocalVariables, "t2");
        Thread t3 = new Thread(threadSafeByLocalVariables, "t3");
        t1.start();
        t2.start();
        t3.start();
    }

    private double avgScore(double[] scores) {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        int count = scores.length;
        double avg = sum / count;
        return avg;
    }
}
