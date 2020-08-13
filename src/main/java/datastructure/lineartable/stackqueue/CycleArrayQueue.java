package datastructure.lineartable.stackqueue;

import datastructure.lineartable.Queue;

import java.util.ArrayDeque;

public class CycleArrayQueue<E> implements Queue<E> {


    private int capacity;
    private int front;//总是指向下一个调用 poll的元素下标
    private int rear;//总是指向下一个调用 offer的元素下标
    private Object[] elementData;


    public CycleArrayQueue(int capacity) {
        this.capacity = capacity;
        elementData =new Object[capacity];
    }

    @Override
    public int size() {
        return (rear + capacity-front)%capacity;
    }

    @Override
    public boolean isEmpty() {
        return front==rear;
    }
    public boolean isFull(){
        return (rear+1)%capacity==front;
    }

    @Override
    public boolean offer(E e) {
        if(isFull()) throw new IllegalArgumentException("队列已满");
        elementData[rear] = e;
        rear = (rear+1)%capacity;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) throw new IllegalArgumentException("队列为空");



        E data =(E) elementData[front];
        front = (front+1)%capacity;
        return data;
    }

    @Override
    public E peek() {
        if(isEmpty()) throw new IllegalArgumentException("队列为空");

        return (E) elementData[front];
    }
}
