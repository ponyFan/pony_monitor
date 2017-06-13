package com.pony.common.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zelei.fan on 2017/6/8.
 *
 * 线程：同一类的线程共享数据空间和代码，每个线程有独立运行的栈和程序计数器切换开销小
 * 状态：创建、就绪、执行、阻塞、终止
 * 多线程是指在同一程序中有多个顺序流在执行
 * 实现多线程的方式：
 * 1、继承thread类
 * 2、实现runnable接口
 * 3、实现callable接口（线程池）
 */
public class ThreadTest extends Thread{

    private String name;

    ThreadTest(String name){
        this.name = name;
    }

    ThreadTest(){}

    private int index = 10;

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"线程开始运行");
        while (index > 0) {
            System.out.println(Thread.currentThread().getName()+name + "运行  :  " + index);
            index --;
        }
        System.out.println(Thread.currentThread().getName()+"线程运行结束");
    }

    public static void main(String[] args) {
        /*注意：继承thread类的实例不能重复调用start()，原因是一个thread实例不能重复调用start()，
        * 因为当前这个thread已经变成可执行状态，当再次调用start()方法时，有可能线程已经在执行过程中了
        * 而线程中除了阻塞和可运行状态之间可以相互逆转之外，其他状态时不能逆转的，所以也就解释了上述问题
        * 运行中不能再次逆转成可运行状态；
        *    但是实现runnable接口则不然，只new一个程序代码，但是多线程的时候是每用一个都是new一个新的thread类来启动这个程序实例
        * 这样就不存在上述thread的那样的问题，同时也实现了多个线程处理同一资源
        * */
        ThreadTest threadTesta = new ThreadTest("A");
        ThreadTest threadTestb = new ThreadTest("B");
        threadTesta.start();
        threadTestb.start();
        /*继承thread类也可以使用同一个实例*/
        new Thread(threadTesta).start();
        new Thread(threadTesta).start();
        new Thread(threadTesta).start();

        RunnableTest a = new RunnableTest("C");
        RunnableTest b = new RunnableTest("D");
        new Thread(a).start();
        new Thread(b).start();

        /*runnable比thread具有的优势：
        *        注意：不管是继承thread或者是实现runnable，线程都是不安全的
        *        1、 适合多个相同的程序代码的线程去处理同一资源；
        *             下面的例子，当用继承thread类时，new出的多个ThreadTest实例，在同时启动线程的时候这几个线程都是在各自运行各自中的资源，
        *         各自的线程中都有index这个资源，并且代码空间也都是互相独立，因为ThreadTest被new出多个实例，这几个线程并不共用同一实例；
        *             但是实现runnable接口就不一样了，new出一个RunnableTest实例后，下面几个线程都是用的同一个实例来启动线程，当执行到程序中时，
        *         每个线程用的代码都是同一个，而且用的资源index也是同一个，这样就实现了多线程对同一资源的处理；
        *
        *         2、可以避免java中的单继承的限制
        *              继承thread：如果ThreadTest需要继承一个父类代码，但是又同时想实现多线程这个功能，那就和java单继承有冲突
        *              实现runnable：可以实现在继承thread的同时再实现runnable接口
        *
        *         3、代码可以被多个线程共享，代码和数据独立
        *              代码共享：都是用的同一实例
        *              代码和数据独立：即代码和数据是分开的，数据是一个公共的资源，个线程之间都能使用
        *
        *         4、线程池中只能放入runnable接口或者callable接口;如下例子
        * */
        RunnableTest runnableTest = new RunnableTest("X");
        new Thread(runnableTest).start();
        new Thread(runnableTest).start();
        new Thread(runnableTest).start();
        new Thread(runnableTest).start();

        /*线程池中只能放入runnable接口或者callable接口*/
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        service.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        /*多线程参数传递
        * 1、构造设值
        * 2、set设值
        * 3、回调设值,把在run方法中处理完结果赋值给对象中的一个成员变量，然后主线程通过获取这个对象的成员变量值来获取结果
        * */
        ThreadTest thread = new ThreadTest("B");
        thread.setName("M");
    }
}
