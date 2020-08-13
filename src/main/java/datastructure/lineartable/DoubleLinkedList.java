package datastructure.lineartable;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;


public class DoubleLinkedList<E> implements LinearList<E>{


    private int  size;
    private Node<E> head;


    public DoubleLinkedList() {
        head =new Node<>();
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
        Node<E> p =head.next;
        for(int num=0;;num++){
            if(p==null)break;
            if(num>=index)break;
            p=p.next;
        }
        if(p==null)return null;
        return p.data;
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
    public void insert(int index, E element) {
        checkIndex4add(index);

        Node<E> p = head;

        for(int num=0;;num++){
            if(p==null || num>=index)break;
            p=p.next;
        }

        if(p==null)return;
        Node<E> eNode = new Node<>(element, p, p.next);
        if(p.next!=null) p.next.prior = eNode;
        p.next =eNode;
        size++;
    }

    @Override
    public void append(E element) {
        Node<E> p= head;
        for(;p.next!=null;){
            p = p.next;
        }
        Node<E> eNode = new Node<>(element, p, null);
        p.next= eNode;
        size++;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> p = head.next;
        for(int num=0;;num++){
            if(p==null)break;
            if(num >=index)break;
            p=p.next;
        }
        if(p==null)return null;
        E old = p.data;
        p.data =element;
        return old;
    }

    @Override
    public E remove(int index) {
        Node<E> p = head.next;
        for(int num=0;;num++){
            if(p==null)break;
            if(num >=index)break;
            p=p.next;
        }
        if(p==null)return null;
        p.prior.next = p.next;
        if(p.next!=null)p.next.prior = p.prior;
        p.next =null;
        p.prior =null;
        E old = p.data;
        size--;
        return old;
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
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("size :"+size+"   ");
        Node<E> p = head;
        if(p==null) return result.toString();
        result.append(" data :");
        result.append(p.toDebugString()+",");
        for(;p.next!=null;){
            p=p.next;
            result.append(p.toDebugString()+",");
        }

        return result.toString();
    }

    public Node<E> getHead() {
        return head;
    }

    class Node<E>{
        E data;
        Node<E> next;
        Node<E> prior;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> prior, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prior = prior;
        }

        public String toDebugString(){
            StringBuffer sql = new StringBuffer();
            sql.append(" 前一个节点数据 {0}" );
            sql.append(" 该节点数据 {1}"  );
            sql.append(" 后一个节点数据 {2}"  );
            String format = MessageFormat.format(sql.toString(), prior == null ? "没有" : prior.data, data, next == null ? "没有" : next.data);
            return format;
        }


    }

    public static void main(String[] args) {
        StringBuffer sql = new StringBuffer();
        sql.append("  前一个节点数据 {0}" );
        sql.append(" 该节点数据 {1}"  );
        sql.append("  后一个节点数据 {2}"  );
        String format = MessageFormat.format(sql.toString(), "1" ,"测试2","测试3");

        System.out.println(format);
    }
}
