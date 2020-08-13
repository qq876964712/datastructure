package datastructure.lineartable;

import java.util.HashMap;
import java.util.List;

public class SingleLoopLinkedList<E> {
    Node<E> head;
    Node<E> rear;// 如果包含 头节点 这个指针可以 去掉的
                // 如果不包含头节点
    private int size;

    public SingleLoopLinkedList(){
        head = new Node<E>();
        head.next = head;
        rear = head;
    }
    
    public boolean isEmpty(){
        return size==0;
    }
    public void insert(int index,E element){
        Node<E> p =head;
        int num=0;
        for(;;){
            if(p ==rear)break;//被插入位置的 前一个位置 是最后一个元素
            if(num >=index)break; //
            num++;
            p=p.next;//  num =0 下标0 p为他的前一个位置   num  下标 num     index   下标index  如果不同说明提前结束
        }
        if(num!=index)return;
         p.next = new Node<E>(element,p.next);
        if(p == rear){
            rear = p.next;
        }
        size++;
    }
    public void append(E element){
        Node<E> p =head.next;
        for(;;){
            if(p ==rear)break;
            p=p.next;
        }
        p.next = new Node<E>(element,p.next);
        rear = p.next;
        size++;
    }
    public void remove(int index){
        if (head.next == head) return;
        Node<E> p =head;
        int num=0;
        for(;;){
            if(p.next.next==head)break;
            if(num >=index)break;
            num++;
            p=p.next;
        }
        if(num!=index) return;
        Node<E> end = p.next;
        p.next =p.next.next;
        if(end == rear)rear =p;
        size--;
    }
    @Override
    public String toString() {
        String str ="";
        Node<E> p =head.next;
        for(;;){
            if(p==head)break;
            str +=p.data+",";
            p =p.next;
        }
        return str;
    }

    public void josePhu( int k) {
        if(k<1)return;
        Node<E> p =head;
        int num =1;
        for (; p.next!=p; ) {
            if (p.next != head) num++;
            p = p.next;
            
            if(k==num && p.next != head){
                System.out.println("出局 : "+ p.next.data );
                p.next =p.next.next;
                num=1;
            }
            
        }
        size=0;
        rear= head;
        
    }

    public void josePhu2(int k) {
        if (k < 1) return;
        Node<E> p = head;
        for (; p.next != p; ) {
            Node<E> q = p.next;
            if(p.next==head){
                p=p.next;
                continue;
            }
            for(int i=1;i<k;){  //经历k次
                p=p.next;
                q=q.next;
                if(q!=head)i++;
            }
            System.out.println("出局 : " + p.next.data);
            p.next =p.next.next;
        }
        size = 0;
        rear = head;

    }
}
class  SingleLoopLinkedList2<E> {
    private Node<E> head;
    private Node<E> rear;
    public SingleLoopLinkedList2(){
    
    }
    
    public void insert(int index, E element) {
        Node<E>p = head;
        Node<E> q = rear;
        int num =0;
        for(;;){
            if(head==null)break;
            if(q.next ==head && q.next!=q )break;
            if(num>=index)break;
            
            num++;
            q= p;
            p=p.next; //0 下标 0  num  下标num
        }
        if(num!=index)return;
        if(head==null) {
            head=new Node<E>(element);
            head.next =head;
            rear = head;
        }else{
            Node<E> theNode = new Node<E>(element, q.next);
            if(q==rear && index==0){
                head =theNode;
            }else if(q == rear && index != 0){
                rear = theNode;
            }
            q.next = theNode;
        }
    }
    
    public void append(E element){
        Node<E> p = head;
        for(;;){
            if(head==null)break;
            if(p.next == head)break;
            p=p.next;
        }
        if(head==null){
            head = new Node<E>(element);
            head.next =head;
            rear =head;
        }else{
            Node<E> eNode = new Node<>(element,p.next);
            p.next =eNode;
            rear = eNode;
        }
    }
    
    public void remove(int index){
        Node<E> p =rear;
        int num =0;
        for(;;){
            if(p==null)return;
            if(p.next ==rear ) break;
            if(num >=index)break;
            num++;
            p=p.next;
        }
        if(num!=index)return;
        if(p.next ==p){
            E oldData = p.data;
            rear =null;
            head =null;
        }else{
            Node<E> eNode =p.next;
            E oldData = p.data;
            p.next=p.next.next;
            if(p==rear){
                head = p.next;
            }
            if(p.next ==head){
                rear =p;
            }
            
        }
        
    }
    
    public void Josephu(int k){
        Node<E> p = rear;
        int num =1;
        for(;;){
            if(head==null)break;
            if(p.next==p){
                System.out.println("出局 : "+p.data);
                break;
            }
            if(num==k){
                num=1;
                Node<E>eNode = p.next;
                System.out.println("出局 : " + eNode.data);
                p.next=p.next.next;
            }
            p=p.next;
            num++;
        }
        head=null;
        rear=null;
        
    }
    
    @Override
    public String toString() {
        String str = "";
        Node<E> p = head;
        for (; ; ) {
            if(p==null)break;
            if (p ==rear) break;
            str += p.data + ",";
            p = p.next;
        }
        if(p==null)return str;
        str+=p.data+",";
        
        return str;
    }
    
}