package com.pony.common.thread;

/**
 * Created by zelei.fan on 2017/6/12.
 */
public class ThreadWaitNotifyTest implements Runnable {

    private Object self;

    private Object prev;

    private String name;

    public ThreadWaitNotifyTest(Object self, Object prev, String name){
        this.self = self;
        this.prev = prev;
        this.name = name;
    }

    int index = 10;

    @Override
    public void run() {
        while (index > 0){
            /*先锁住前一个线程的对象锁，这样前一个线程是执行不了的，除非等到该锁被释放*/
            synchronized (prev){
                /*再锁住本身的对象锁，使得下一个持有当前线程的对象锁的线程不能执行，
                * 简单说就是b持有者a的锁，把线程走到第一个锁的时候就不能通过，除非a执行完成后把锁释放
                * */
                synchronized (self){
                    System.out.println(name);
                    index--;
                    /*a执行完成后，唤醒其它在等待的b线程和c线程*/
                    self.notify();
                }
                /*当a释放了自身对象的锁的时候，b线程就能获取对象锁执行，但是在执行wait之前要先等待一下
                * 主要是为了给b线程占用，因为在调用wait的时候c线程的对象锁会被释放，这样c线程会和b线程抢占执行
                * 当a对象锁一释放，该线程要停留一段时间，b和c都在等待执行，但是c持有a的对象锁和自身对象锁，a的释放了，自身的
                * 还没有释放，等sleep时间到了之后调用wait时才会释放；
                * */
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
