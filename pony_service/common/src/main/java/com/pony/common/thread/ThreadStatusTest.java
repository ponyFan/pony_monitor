package com.pony.common.thread;

/**
 * Created by zelei.fan on 2017/6/9.
 * 线程状态转换详情
 * 过程：
 * 1、创建一个线程（new Thread()）
 * 2、可运行状态（调用start()方法）
 * 3、正常情况下，获取到cpu使用权，直接执行
 * 4、阻塞：
 *       1）、等待阻塞，线程调用wait()方法，jvm会吧该线程放到等待池中；
 *       2）、同步阻塞，线程在获取同步锁时，同步锁还在被其他线程占用中，jvm把该线程放到锁池中；
 *       3）、其他阻塞，调用sleep()或者join()，线程sleep超时或者join等到其他线程终止，改线成则重新回到可执行状态；（注意执行sleep时不释放锁）
 * 5、线程结束
 */
public class ThreadStatusTest {

    /**
     * 线程调度
     */
    public void threadDispatch() throws Exception{
        Thread.sleep(100);
        new Thread().wait();/*线程等待，等到其他线程调用notify()或者notifyAll()方法来唤醒这些wait的线程*/
        Thread.yield();/*线程让步，把资源让给优先级高的线程*/
        new Thread().join();/*等待其他线程终止，在当前线程中调用另一个线程的join方法，则当前线程进入阻塞状态，直到另一个线程完成菜进入就绪状态*/
        new Thread().notify();/*唤醒在等待的线程中的某一个线程，随机唤醒其中一个；notifyAll()唤醒所有线程*/
    }

    public static void main(String[] args) {

    }
}
