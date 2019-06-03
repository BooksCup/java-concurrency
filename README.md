# java-concurrency
## 锁
### 公平锁、非公平锁
  公平锁:   多个线程按照申请锁的顺序来获取锁  
  非公平锁: 没有顺序完全随机,可能会导致优先级反转或者饥饿现象  
  synchronized 就是典型的非公平锁  
  ReentrantLock通过构造参数可以决定是非公平锁还是公平锁，默认构造是非公平锁  
  非公平锁的吞吐量性能比公平锁好很多  
  (公平锁、非公平锁)代码实例:  
  https://github.com/BooksCup/java-concurrency/blob/master/src/main/java/com/bc/concurrency/lock/FairLock.java
  引申: 线程饥饿问题
  多线程执行时有线程优先级的概念，优先级高的线程能够插队并优先执行，这样如果优先级高的线程一直抢占优先级低线程的资源，导致低优先级线程无法得到执行，这就  是饥饿。还有一种饥饿的情况，就是一个线程一直占着一个资源不放而导致其他线程得不到执行。
