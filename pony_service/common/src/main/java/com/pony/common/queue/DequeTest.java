package com.pony.common.queue;

/**
 * Created by zelei.fan on 2017/6/14.
 * deque在queue的基础上实现了能取出第一个元素(offerFirst()/offerLast(),addFirst()/addLast()),
 * 取出最后一个元素(pollFirst()/pollLast(),removeFirst()/removeLast())
 *
 * 正因为deque能够选择队尾或者队头，所以deque不仅能实现FIFO，同时还能实现LIFO；
 */
public class DequeTest {


    /*实现类有ArrayDeque和LinkedList
    * ArrayDeque并不是定长，每次队列满了后都会在增长一倍，索引更快，不需要entry
    *
    * */
    public static void main(String[] args) {

    }
}
