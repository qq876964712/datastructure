package datastructure.lineartable;

import java.util.function.Predicate;
import static datastructure.lineartable.LinkedList1.Node;

public class LinkedList2<E> implements LinearList<E>{

    private Node<E> head;
    private int size;

    public LinkedList2() {
        head = new Node<E>();
    }
    private void checkIndex4add(int index){
        if(index<0 || index>size){
            throw new IllegalArgumentException("");
        }
    }
    private void checkIndex(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("");
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public E get(int index) {
        Node<E>  p = head.next;
        for(int num=0;p!=null && num<index;num++){
            p = p.next;
        }

        return p==null? null:p.data ;
    }

    @Override
    public void insert(int index, E element) {
        checkIndex4add(index);
        Node<E> prior = head;
        for(int num =0;prior!=null && num<index ;num++){
            prior =prior.next;
        }

        prior.next = new Node<>(element, prior.next);
        size++;
    }

    @Override
    public void append(E element) {
        Node<E> p = head;
        for(;p.next!=null;){
            p = p.next;
        }
        p.next = new Node<E>(element,p.next);
        size++;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E>  p = head.next;
        for(int num =0; p!=null && num<index; num++){
            p =p.next;
        }
        E old = p.data;
        p.data =element;
        return old;
    }

    @Override
    public E remove(int index) {
        if (index<0) return null;
        Node<E> p = head;
        int num=0;
        for(;p!=null && num<index;num++){
            p =p.next;
        }
        if(p!=null && p.next!=null){
            E old = p.next.data;
            p.next= p.next.next;
            size--;
            return old;
        }
        return null;
    }

    @Override
    public E search(Predicate<E> e) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("size :"+size+"   ");
        Node<E> p = head;
        if(p==null) return result.toString();
        result.append(" data :");
        result.append(p.data+",");
        for(;p.next!=null;){
            p=p.next;
            result.append(p.data+",");
        }

        return result.toString();
    }

}

