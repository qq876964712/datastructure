package datastructure.lineartable;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 
 *带头节点的  采用尾插
 */
public class SingleLinkedList<E> implements LinearList<E> {

	protected Node<E> head; //带有头的链表  和不带头的链表

	public SingleLinkedList() {
		head = new Node<E>();
	}
	public SingleLinkedList(E[]elements) {
		this();
		Objects.requireNonNull(elements);
		Node<E> rear = this.head;
		for(int i=0;i<elements.length;i++) {
			rear.next = new Node<E>(elements[i]);
			rear=rear.next;
		}
	}
	/**
	 * 单链表的深拷贝
	 * @param list
	 */
	public SingleLinkedList(SingleLinkedList<E> list) {
		this();
		Objects.requireNonNull(list);
		Node<E> rear = this.head;
		Node<E> p = list.head.next;
		while(p!=null) {
			rear.next =new Node<E>(p.data);
			rear = rear.next;
			p=p.next;
		}
				
	}
	
	private Node<E> create(E[] elements ,int index){
		Node<E> p=null;
		if(index<elements.length) {
			Node<E>rear = null;
		   p=	new Node<E>(elements[index]);
		   p.next =create(elements, index+1); 
		}
		return p;
	}
	@Override
	public int size() {
		Node<E> p = this.head;
		int i=0;
		while(p.next!=null) {
			i++;
			p=p.next;
		}
		return i;
	}

	@Override
	public boolean isEmpty() {
		return this.head.next==null;
	}

	@Override
	public E get(int index) {
		if(index<0) return null;
		Node<E> p = this.head.next;
		for(int i=0;i< index  && p!=null ;i++) {
			p=p.next;
		}
		return p==null?null:p.data;
	}

	@Override
	public void insert(int index, E element) {
		if(index<0)throw new IndexOutOfBoundsException(""+index);
		Node<E> prior = this.head;
		int i=0;
		//找到index 位置前面哪个元素
		for(;i<index  && prior!=null;i++) {
			prior = prior.next;
		}	
		if(prior==null)throw new IndexOutOfBoundsException(""+index);
		prior.next = new Node<E>(element,prior.next);
	}

	@Override
	public void append(E element) {
		Node<E> p = this.head;
		for(;p.next!=null;) {
			p = p.next;
		}
		p.next = new Node<E>(element,null);
	}

	@Override
	public E set(int index, E element) {
		Node<E> p = this.head.next;
		
		if(index>0) {
			for(int i=0;i<index && p!=null ;i++) {
				p = p.next;
			}
			E oldData =null;
			if(p!=null) {
				oldData = p.data;
				p.data = element;
			}
			return oldData;
		}
		
		return null;
				
			
	}

	@Override
	public E remove(int index) {
		Node<E> prior = this.head;
		int i =0;
		for(;i<index && prior.next!=null; i++) {
			prior = prior.next;
		}
		
		Node<E> oldNode= prior.next;
		if(oldNode!=null) {
			prior.next= oldNode.next;
			oldNode.next = null;
			return oldNode.data;
		}
		return null;
	}

	@Override
	public E search(Predicate<E> e) {
		Node<E> p = head;
		for(;p.next!=null;) {
			p = p.next;
			if(e.test(p.data))
				return p.data;
		}
		
		return null;
	}

	@Override
	public boolean contains(Object o) {
		Node<E> p = head;
		for(;p.next!=null;) {
			p=p.next;
			if(  (o==null && p.data==null )  || o.equals(p.data)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "data : "+ head.next;
	}

	public void reverse(){
		Node<E>   data =this.head.next;
		Node<E> eNode = Node.reverse3(data);
		this.head.next = eNode;
	}

	@Override
	public void clear() {
		head.next=null;
	}
	
	
 private class AAA{
		private String name;
	}

	
	public static void main(String[] args) {

		SingleLinkedList<String> list = new SingleLinkedList<>();

		list.append("111");
		list.append("222");
		list.append("333");
		list.append("444");

		System.out.println(list.toString());

		list.reverse();
		System.out.println(list.toString());

		System.out.println(list.get(1));

		list.remove(1);
		System.out.println(list.toString());


	}
	
}
