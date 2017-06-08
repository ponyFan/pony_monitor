package com.pony.common.collection;

import com.beust.jcommander.internal.Lists;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by zelei.fan on 2017/6/7.
 */
public class ListTest {

    public void ship(List list){
        Person p1 = new Person();
        p1.setName("haha");
        p1.setAge(10);
        list.add(p1);

        Person p2 = new Person();
        p2.setName("cc");
        p2.setAge(15);
        list.add(p2);

        Person p3 = new Person();
        p3.setName("ee");
        p3.setAge(12);
        list.add(p3);

        Person p4 = new Person();
        p4.setName("ee");
        p4.setAge(12);
        list.add(p4);
    }

    public void arrayListTest(){
        /*ArrayList代表长度可变的数组。允许对元素进行快速的随机访问
        * LinkedList和ArrayList没太大区别，除了是链表结构，在插入和删除的上性能较高
        * 差异原因：     主要是结构决定了，ArrayList是一个有序的结构，每个节点都有一个索引值，
        *           所以在查询的时候按索引来查找很快；但是在插入和删除的时候性能就差了，
        *           是因为如果插入某个节点时，该节点的后面所有节点的索引都要加1，删除也是同样道理，
        *           后面的都要减1，如果list长度太大时会很影响性能；
        *               而LinkedList，查询就比较慢，因为没有索引，需要通过碰撞法遍历去查询，影响性能；
        *            它的结构是链表结构，双链表，每个元素都记录着它前一个元素的id和后一个元素的id，
        *            所以在插入的时候只要记录下前一个元素的id和后一个元素的id即可，删除即是把前一个元素的右手边
        *            改成该元素的右手边的id，把后一个元素的左手边改成该元素的左手边的id；
        *
        *
        * */
        List<Person> arrayList = Lists.newArrayList();
        ship(arrayList);
        /*基本方法*/
        arrayList.size();
        arrayList.clear();
        arrayList.isEmpty();/*断集合是否为空，为空返回true*/
        arrayList.remove(1);/*按索引移除某个位置的值*/

        /*遍历方法*/
        /*function 1, 该方法可以在遍历过程中对元素做删除*/
        Iterator<Person> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            Person person = iterator.next();
            iterator.remove();
            System.out.println(person);
        }

        /*function 2*/
        for (Person p : arrayList){
            Person person = p;
        }

        /*function 3*/
        for (int i = 0; i < arrayList.size(); i ++){
            Person p = arrayList.get(i);
        }

        /*function 4*/
        arrayList.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                Person p = person;
            }
        });

        /*function 5*/
        arrayList.stream().forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                Person p = person;
            }
        });

    }

    public void setTest(){
        /*set主要是去重复
        * Set判断两个对象相同不是使用==运算符，而是根据equals方法。
        * 处理基本类型之外的其它对象在判断equals方法为true的时候会再判断hashcode
        * 值是否相等，相等时才会返回true
        * set的及格实现类都是线程不安全的
        * 区别：
        * hashSet和treeSet的区别，hashSet的性能总是比treeSet好，新增，查询。。，
        * 主要是因为treeSet一直都要红黑树算法来维持集合顺序，出来排序之外都应该优先使用hashSet
        * LinkedHashSet是hashSet的子类，
        * */
        /*hashSet按哈hash算法来存储元素，所以在查找和存储性能上比较好
        * 无序的，线程不安全，值可以为null
        * hashSet 通过对象的hashCode值来决定对象存储的位置；
        * */

        List<Person> personList = Lists.newArrayList();
        ship(personList);
        Set hashSet = new HashSet<Person>(personList);/*自动去重list中的重复对象*/



        /*treeSet能够实现排序功能
        * */
        Set<Person> treeSet = new TreeSet<Person>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        treeSet.addAll(personList);

    }


    public void mapTest(){

        Map<Integer, String> map = new HashMap();
        /*基本方法*/
        map.clear();
        map.put(1, "a");
        map.remove(1);
        map.putAll(new HashMap<Integer, String>());

        map.entrySet();/*所有键值对*/
        map.keySet();/*所有key*/
        map.values();/*所有值*/

        /*遍历方法*/
        for (Integer k : map.keySet()){
            int key = k;
            String value = map.get(k);
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()){
            int key = entry.getKey();
            String value = entry.getValue();
        }

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, String> it = iterator.next();
            System.out.println(iterator.next());
        }

        /*treeMap自动排序，按照红黑树算法存入的时候就按hashcode排序*/
        Map<Integer, String> treeMap = new TreeMap<Integer, String>();
        treeMap.put(4, "ss");
        treeMap.put(2, "ss");
        treeMap.put(6, "ss");
        treeMap.put(1, "ss");
        System.out.println(treeMap);/*与treeSet一样存储*/
    }

    public static void main(String[] args) {
        ListTest collection = new ListTest();
        List<Person> list = Lists.newArrayList();
        collection.ship(list);
        Set<Person> set = new HashSet<Person>(list);
        System.out.println(set);

        Set<Person> treeSet = new TreeSet<Person>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        treeSet.addAll(list);
        System.out.println(treeSet);
    }
}
