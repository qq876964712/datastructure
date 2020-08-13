package datastructure.lineartable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Predicate;

public class LinkedList1<E> implements LinearList<E>{

    private int size;
    private Node<E> head; //先模拟 不带头节点


    @Override
    public int size() {
        return size;
    }

    public LinkedList1() {
    }

    public LinkedList1(E...data) {
        Objects.requireNonNull(data);
        Node<E> p = head;
        for(E element:data){
            p =  new Node<E>(element);
            p= p.next;
            size++;
        }

    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public E get(int index) {
        Node<E> p =head;
        for(int num =0; num<index;num++){
            p =p.next;
            if(p==null) break;
        }
        return p==null?null:p.data;
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

        Node<E> prior =null;
        Node<E> p = head;

        if(index==0){
            head =  new Node<>(element, head);
            size++;
            return;
        }
        for( int num=0;num<index;num++){
            if(p==null) break;
            prior = p;
            p = p.next;
        }

        if(prior==null) return;
        prior.next = new Node<>(element, prior.next);
        size++;
    }
    public void appendByOrder(E element,Comparator<E> comparator){

        Node<E> p = head;
        Node<E> q = null;
        for(;;){
            if(p==null)break;
            if(comparator.compare(element,p.data) <0){
                break;
            }
            q=p;
            p=p.next;
        }
        if(q!=null){
            q.next = new Node<>(element,q.next);
        }else{
            head = new Node<E>(element,head);
        }
        size++;
    }

    public static <E> Node<E> getTheNode(Node<E> node1,Node<E> node2,Comparator<E> comparator){
        Node<E> result =null;
        Node<E> p =null;
        Node<E> p1 =node1;
        Node<E> p2 =node2;
        for(;;){
            if(p1==null || p2 ==null)break;
            if(comparator.compare(p1.data,p2.data)<=0){
                if(p==null){
                   p= new Node<E>(p1.data,null);
                   result =p;
                }else{
                   p.next =new Node<E>(p1.data,null);
                   p = p.next;
                }
                p1 =p1.next;
            }else{
                if(p==null){
                    p= new Node<E>(p2.data,null);
                    result =p;
                }else{
                    p.next =new Node<E>(p2.data,null);
                    p = p.next;
                }
                p2 =p2.next;
            }

        }

        while(p1!=null){
            p.next =new Node<E>(p1.data,null);
            p = p.next;
            p1 = p1.next;
        }
        while(p2!=null){
            p.next =new Node<E>(p2.data,null);
            p = p.next;
            p2 = p2.next;
        }

        return result;
    }
    public void appendByOrderWithHeadNode(E element,Comparator<E> comparator){

        if(head==null){
            head = new Node<E>();
        }
        Node<E> p = head.next;
        Node<E> q = head;
        for(;;){
            if(p==null)break;
            if(comparator.compare(element,p.data) <0){
                break;
            }
            q=p;
            p=p.next;
        }
        q.next = new Node<>(element,q.next);
        size++;
    }
    public void appendByOrder(Comparator<E> comparator,E... elements){
        if(elements!=null){
            for(E e : elements){
                appendByOrder(e,comparator);
            }
        }
    }

    @Override
    public void append(E element) {
        Node<E> p = head;
        if(p==null){
            head =  new Node<>(element);
            size++;
            return;
        }
        for(;p.next!=null;){
            p=p.next;
        }
        p.next = new Node<>(element);
        size++;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        int num =0;
        Node<E> p = head;
        for(;p!=null  && num<index;){
            p=p.next;
            num++;
        }

        if(p==null)return null;

        E old = p.data;
        p.data = element;
        return old;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        int num =0;
        Node<E> prior =head;
        if(index==0){
           E data = head.data;
           head = head.next;
           size--;
           return data;
        }


        for(;prior!=null && num < index-1;){
            prior = prior.next;
            num++;
        }
        size--;
        E data = prior.next.data;
        prior.next = prior.next.next;
        return data;
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

    public Node<E> getHead() {
        return head;
    }

    static class Node<E>{
        E data;
        Node<E> next;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }


        public static <E> Node<E> reverse1(Node<E> node){
            Node<E> head= null;
            Node<E> p =node;
            for(;p!=null;){
                Node<E> q =p.next;
                p.next = head;
                head =p;
                p= q;
            }

            return head;
        }

        public static <E> Node<E> reverse2(Node<E> node){
            Node<E> bound =node;
            Node<E> head =bound;
            if(bound ==null)return null;
            for(;bound.next!=null;){
                Node<E>q =bound.next ;
                bound.next =q.next;
                q.next =head;
                head =q;
            }
            return head;
        }
        public static <E> Node<E> reverse3(Node<E> node){
            Node<E> p =node;
            if(p==null || p.next==null){
                return p;
            }
            Node<E> eNode = reverse3(p.next);
            p.next.next =p;
            p.next =null;
            return eNode;
        }

        public String toReverseString1(){

            StringBuffer sql =new StringBuffer();
            ArrayList<Node<E>> nodes = new ArrayList<>();
            Node<E> p = this;

            for(;p!=null;){
                nodes.add(p);
                p=p.next;
            }
            for( int i = nodes.size()-1;i>=0;i--){
                sql.append(nodes.get(i).data);
                if(i<nodes.size())sql.append(",");
            }
            return sql.toString();
        }

        public String toReverseString2(){

            StringBuffer sql =new StringBuffer();
            Stack<Node<E>> nodes = new Stack<>();
            Node<E> p = this;

            for(;p!=null;){
                nodes.push(p);
                p=p.next;
            }

            for(;nodes.size()!=0;){
                p=nodes.pop();
                sql.append(p.data);
                if(nodes.size()!=0)  sql.append(",");
            }
            return sql.toString();
        }
        public String toString() {
            StringBuffer result = new StringBuffer();
            Node<E> p = this;
            if(p==null) return result.toString();
            result.append(p.data+",");
            for(;p.next!=null;){
                p=p.next;
                result.append(p.data+",");
            }
            return result.toString();
        }
    }


}
