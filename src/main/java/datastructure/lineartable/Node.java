package datastructure.lineartable;

public class Node<E> {
    public E data;
    public Node<E> next;
    public Node() {
        super();
    }
    public Node(E data) {
        super();
        this.data = data;
    }


    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public static <E>void reverse(Node<E> nodes) {



    }
    /**reverse1 和reverse2可以理解为内部 和借助另一条链
     * 这种方法以第一个元素为基准   bound.next 会自动往后推 ,因为每一次 都会将bound.next=q 取出 ,然后将bound.next指向bound.next.next,
     *然后将q 首插
     */
    public static<E> Node<E>  reverse1(Node<E> nodes){
        Node<E> head = nodes;
        Node<E> bound = head;
        if(bound==null ||bound.next==null)return head;
        while(bound.next!=null) {
            Node<E> q = bound.next;
            bound.next = bound.next.next;
            q.next = head;
            head =q;
        }
        return head;
    }

    /**
     * 先断开head 将节点一个一个首插到head上
     */
    public static<E>  Node<E>  reverse2(Node<E> nodes){
        Node<E> p = nodes;
        Node<E> head = null;
        while(p !=null) {
            Node<E> q = p;
            p = p.next;

            q.next=head;
            head =q;

        }
        return head;
    }
    public static<E>  Node<E> reverse3(Node<E> nodes){
        if(nodes==null)return null;
        Node<E> p = nodes;
        if(p.next!=null) {
            Node<E> subReverse = reverse3(p.next);
            p.next.next = p;
            p.next = null;
            p = subReverse;
        }
        return p;
    }




}