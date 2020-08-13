package datastructure.lineartable.stackqueue;

import datastructure.lineartable.Node;

public class LinkedStack<E> implements  Stack<E> {
    
    private Node<E> top;
    @Override
    public E pop() {
        if (isEmpty()) throw new IllegalArgumentException("空栈!");
        E popData = top.data;
        top = top.next;
        return popData;
    }
    
    @Override
    public void push(E element) {
        if (isFull()) throw new IllegalArgumentException("空栈!");
        top =new Node<E>(element,top);
    }
    
    @Override
    public E peek() {
        if (isEmpty()) throw new IllegalArgumentException("空栈!");
        return top.data;
    }
    
    @Override
    public boolean isEmpty() {
        return top==null;
    }
    
    @Override
    public boolean isFull() {
        return false;
    }
    
    @Override
    public String toString() {
        String str="";
        Node<E> p =top;
        for(;;){
            if(p==null)break;
            str+=p.data+",";
            p=p.next;
        }
        return str;
    }
}
