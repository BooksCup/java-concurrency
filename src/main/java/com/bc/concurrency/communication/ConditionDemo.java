package com.bc.concurrency.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信
 * Condition的例子
 *
 * @author zhou
 */
public class ConditionDemo {
    private int singal;
    Lock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();

    public void printA() {
        lock.lock();
        while (singal != 0) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        singal++;
        b.signal();
        lock.unlock();
    }

    public void printB() {
        lock.lock();
        while (singal != 1) {
            try {
                b.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        singal++;
        c.signal();
        lock.unlock();
    }

    public void printC() {
        lock.lock();
        while (singal != 2) {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        singal = 0;
        a.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        FirstClass firstClass = new FirstClass(conditionDemo);
        SecondClass secondClass = new SecondClass(conditionDemo);
        ThirdClass thirdClass = new ThirdClass(conditionDemo);

        new Thread(firstClass).start();
        new Thread(secondClass).start();
        new Thread(thirdClass).start();
    }

}

class FirstClass implements Runnable {

    private ConditionDemo conditionDemo;

    public FirstClass(ConditionDemo conditionDemo) {
        this.conditionDemo = conditionDemo;
    }

    @Override
    public void run() {
        while (true) {
            conditionDemo.printA();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondClass implements Runnable {
    private ConditionDemo conditionDemo;

    public SecondClass(ConditionDemo conditionDemo) {
        this.conditionDemo = conditionDemo;
    }

    @Override
    public void run() {
        while (true) {
            conditionDemo.printB();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThirdClass implements Runnable {
    private ConditionDemo conditionDemo;

    public ThirdClass(ConditionDemo conditionDemo) {
        this.conditionDemo = conditionDemo;
    }


    @Override
    public void run() {
        while (true) {
            conditionDemo.printC();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



