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
