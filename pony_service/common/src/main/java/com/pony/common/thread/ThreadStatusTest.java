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
        Thread.sleep(100);/*sleep不会释放对象锁，而wait在调用后会释放对象锁*/
        new Thread().wait();/*线程等待，等到其他线程调用notify()或者notifyAll()方法来唤醒这些wait的线程*/
        Thread.yield();/*线程让步，把资源让给优先级高的线程*/
        new Thread().join();/*等待其他线程终止，在当前线程中调用另一个线程的join方法，则当前线程进入阻塞状态，直到另一个线程完成菜进入就绪状态*/
        new Thread().notify();/*唤醒在等待的线程中的某一个线程，随机唤醒其中一个；notifyAll()唤醒所有线程*/
        Thread.interrupted();/*终止线程，在线程阻塞的时候（join,sleep）执行会导致InterruptedException异常*/
    }

    public static void main(String[] args) throws InterruptedException {
        /*join用法
        * 作用：当主线程需要子线程的执行结果时，主线程必须等待子线程执行结束才能继续执行
        * */
        /*System.out.println("主线程运行开始");
        ThreadTest a = new ThreadTest("A");
        ThreadTest b = new ThreadTest("B");
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("主线程运行结束");


        *//*yield用法
        * 线程让步，让当前线程回到可执行状态，允许相同优先级的其它线程获得执行机会；
        * jvm随机选出某个线程，有可能还会选当前这个线程，都有可能
        **//*



        *//*setPriority()设置优先级
        *MIN_PRIORITY = 1；NORM_PRIORITY = 5;MAX_PRIORITY = 10
        **//*
        ThreadTest c = new ThreadTest("C");
        ThreadTest d = new ThreadTest("D");
        c.setPriority(Thread.MAX_PRIORITY);
        d.setPriority(Thread.MIN_PRIORITY);


        *//*wait()和notify()需要和synchronized来一起使用
        * 如果某个线程执行wait的话，那说明该线程是占有cpu资源的，就是说当前线程是持有对象锁的，
        * 当调用wait的话，该线程就主动释放了对象锁，则线程处于休眠状态，直到其它线程调用notify来唤醒
        * *//*
        Object e = new Object();
        Object f = new Object();
        Object g = new Object();
        *//*每个线程都持有前一个线程的对象锁和本身的一个对象锁，环环相扣*//*
        ThreadWaitNotifyTest te = new ThreadWaitNotifyTest(e, g, "A");
        ThreadWaitNotifyTest tf = new ThreadWaitNotifyTest(f, e, "B");
        ThreadWaitNotifyTest tg = new ThreadWaitNotifyTest(g, f, "C");
        new Thread(te).start();
        new Thread(tf).start();
        new Thread(tg).start();*/

        Object a = new Object();
        Object b = new Object();
        SortThread sortThread = new SortThread("A", a, b);
        SortThread sortThread1 = new SortThread("B", b, a);
        new Thread(sortThread).start();
        new Thread(sortThread1).start();
    }
}

class SortThread implements Runnable{

    private int index = 10;

    private String name;

    private Object prev;

    private Object self;

    SortThread(String name, Object prev, Object self){
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        while (index > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(name);
                    self.notify();
                    index--;
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
