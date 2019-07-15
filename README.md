# java-concurrency
## 锁
### 公平锁、非公平锁
  公平锁:   多个线程按照申请锁的顺序来获取锁  
  非公平锁: 没有顺序完全随机,可能会导致优先级反转或者饥饿现象  
  synchronized 就是典型的非公平锁  
  ReentrantLock通过构造参数可以决定是非公平锁还是公平锁，默认构造是非公平锁  
  非公平锁的吞吐量性能比公平锁好很多  
  [公平锁、非公平锁代码实例](https://github.com/BooksCup/java-concurrency/blob/master/src/main/java/com/bc/concurrency/lock/FairLock.java)
  ##### 引申: 线程饥饿问题  
  多线程执行时有线程优先级的概念，优先级高的线程能够插队并优先执行，这样如果优先级高的线程一直抢占优先级低线程的资源，导致低优先级线程无法得到执行，这就  是饥饿。还有一种饥饿的情况，就是一个线程一直占着一个资源不放而导致其他线程得不到执行。  
  `（线程饥饿）代码实例:`  
  https://github.com/BooksCup/java-concurrency/blob/master/src/main/java/com/bc/concurrency/lock/HungerDeadLock.java  

### 可重入锁  
  可重入锁:可重复可递归调用的锁，在外层使用锁之后，在内层依然可以使用，并且不会发生死锁（前提得是同一个对象或者class），这样的锁就叫可重入锁。  
  ReentrantLock和synchronized都是可重入锁。  
  相对来说，可重入就意味着：线程可以进入任何一个它已经拥有的锁所同步着的代码块。
### 读写锁  
  读写锁是一种特殊的自旋锁，它把对共享资源对访问者划分成了读者和写者，读者只对共享资源进行访问，写者则是对共享资源进行写操作。读写锁在ReentrantLock上进行了拓展使得该锁更适合读操作远远大于写操作对场景。一个读写锁同时只能存在一个写锁或者多个读锁，但不能同时存在写锁和读锁。
  如果读写锁当前没有读者，也没有写者，那么写者可以立刻获的读写锁，否则必须自旋，直到没有任何的写锁或者读锁存在。如果读写锁没有写锁，那么读锁可以立马获取，否则必须等待写锁释放。  
  `(读写锁) 代码实例:`  
  https://github.com/BooksCup/java-concurrency/blob/master/src/main/java/com/bc/concurrency/lock/ReentrantReadWriteLockDemo.java  
  `(读写锁) 测试代码实例:`  
  https://github.com/BooksCup/java-concurrency/blob/master/src/test/java/com/bc/concurrency/test/lock/ReentrantReadWriteLockDemoTest.java  
