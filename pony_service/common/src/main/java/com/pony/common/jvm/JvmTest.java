package com.pony.common.jvm;

import com.beust.jcommander.internal.Lists;

import java.util.List;
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
        * 基本类型的定义都是存在栈中，而包装类型是普通对象，数据存放在堆中
        * */
        int e = 3;
        Integer f = new Integer(3);
        System.out.println(e == f);/*结果：true；当int类型和integer比较时，integer会自动拆箱成int类型，所以e，f比较的是int类型的值*/

        /*下面的例子结果是因为integer中默认的吧常用数字区间-128~127之间的数字缓存起来，
        * 所以当两个integer（注意是没有new的情况下）比较时在这区间内的返回的是true，而大于这个范围的返回的是false
        *
        * new的对象肯定是不同，与缓存的区间无关，因为是重新开辟的空间
        * */
        Integer g = 100;
        Integer h = 100;
        Integer g1 = 150;
        Integer h1 = 150;
        System.out.println(g == h);/*true*/
        System.out.println(g1 == h1);/*false*/

        Integer g2 = new Integer(50);
        Integer h2 = new Integer(50);
        System.out.println(g2 == h2);/*false*/

        /*String是比较特殊的包装类，它并不属于基本类型
        * */
        String i = "abc";
        String j = "abc";
        String k = new String("abc");
        String l = new String("abc");
        System.out.println(i == j);/*true: String创建的常量都存放在常量池中*/
        System.out.println(j == k);/*false: new出来的都是独立的一个，string的是存在栈中的，不共享数据*/
        System.out.println(k == l);/*false：*/

        /*final,修饰的变量不变，引用本身的不变和引用指向不变;
        * 由下面的例子可见，final只是对地址有效，迫使引用只能指向那个对象，但是对象里面值得变化并不管
        *
        * 注意：final修饰的类变量（成员变量）必须赋初始值
        * */
        final StringBuffer m = new StringBuffer("immutable");
        final StringBuffer n = new StringBuffer("not immutable");
        /*m=n;//编译期错误*/
        n.append("qqq");/*编译成功，引用指向不变，n指向的还是new StringBuffer("not immutable")这个对象，只是把对象中的内容改变了*/
        String m1 = "qq";
        m1 = m1 + "ww";/*这里m1其实是重新指向到数据为qqww的这个指向，而原来的qq就不被引用，这样会浪费内存空间*/
        final List<Integer> list = Lists.newArrayList();
        list.add(1);

        /*
        * 初始化不同类型对象需要花费的时间：
        *   本地赋值：i = n; 1.0
        *   实例赋值：this.i = n; 1.2
        *   方法调用：func(); 5.9
        *   新建对象：New Object(); 980
        *   新建数组：New int[10]; 3100
        * */
      }
}

class Dog {
    Dog c;
    String name;
    //1.main()方法位于栈上
    public static void main(String[] args) {
    //2.在栈上创建引用变量d,但Dog对象尚未存在
        Dog d;
    //3.创建新的Dog对象，并将其赋予d引用变量
        d = new Dog();
    //4.将引用变量的一个副本传递给go()方法
        d.go(d);
    }
    //5.将go()方法置于栈上，并将dog参数作为局部变量
    void go(Dog dog){
    //6.在堆上创建新的Collar对象，并将其赋予Dog的实例变量
        c = new Dog();
    }
    //7.将setName()添加到栈上，并将dogName参数作为其局部变量
    void setName(String dogName){
    //8.name的实例对象也引用String对象
        name =dogName;
    }
    //9.程序执行完成后，setName()将会完成并从栈中清除，此时，局部变量dogName也会消失，尽管它所引用的String仍在堆上
}