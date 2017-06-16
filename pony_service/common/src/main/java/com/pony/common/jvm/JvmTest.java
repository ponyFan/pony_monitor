package com.pony.common.jvm;

import java.util.Vector;

/**
 * Created by zelei.fan on 2017/6/15.
 * java内存管理机制
 */
public class JvmTest {

    private static Vector vector = new Vector();

    public static void main(String[] args) {

        /*
        * 内存回收(gc)
        * system.gc():调用该函数会通知内存回收，但不一定会回收，gc的线程优先级是比较低的，不一定取到cpu资源
        * 当一个对象不可达时即失去引用，则会被回收;
        * a的对象赋值给b，那么b也指向a的对象，而b原来的对象则失去引用；
        * 要确定对象占用内存被回收，在确定不需要使用的情况下，将成员变量设置为null，或者将对象从集合中移除；
        * 但是局部变量不需要这样，因为局部变量会随着方法体的结束而消亡
        * */
        Object a = new Object();
        Object b = new Object();
        b = a;

        /*
        * 内存泄露
        * 有些对象是被引用的，但是这些对象在后面的程序中不会被用到；这种情况就是内存泄露，gc不会回收，但是却始终占着内存
        * */
        for (int i = 0; i < 10; i ++){
            Object o = new Object();
            vector.add(o);
            /*
            * 虽然释放了引用o，但是object对象都还被vector引用着，所以这些对象还是不可回收的；
            * 只有当vector也设置为null的时候，这些对象才会被回收
            * */
            o = null;
        }

        /*堆和栈
        * 栈：保存的是基本类型的变量，以及对象引用变量即地址；
        *     栈内存属于单个线程，每个线程都有一个栈内存，互相之间不干扰；
        * 堆：堆中存储的是new的对象和数组，会保存在堆中，但是分配内存空间会消耗比较长的时间；
        * */

        /*基本数据类型：byte,short,int,long,double,float,boolean,char
        * 由于基本类型的指向的数据的字面值大小都是可知的，所以存放在栈中
        * 存放在栈中的数据可以共享:
        *     如下面的例子，首先创建一个变量为a的引用，再查找有没有字面值为2的地址，然后a指向这个地址
        *     如果没有值为3的这个地址，那么会开辟一个；当b查找是发现已经存在了，就直接指向该地址；
        * */
        int c = 2;
        int d = 2;

        /*包装类：Integer,Double等，
        * 基本类型的定义都是存在栈中，而包赚类型是普通对象，数据存放在堆中
        * */
        int e = 3;
        Integer f = new Integer(3);
        System.out.println(e == f);

      }
}
