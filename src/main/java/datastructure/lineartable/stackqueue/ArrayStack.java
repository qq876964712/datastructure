package datastructure.lineartable.stackqueue;

public class ArrayStack<E>implements  Stack<E> {
    
    private Object[] elementData;
    private int top;
    private int capacity;
    
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        elementData =new Object[capacity];
    }
    
    @Override
    public E pop() {
        if(isEmpty())throw new IllegalArgumentException("空栈!");
        return (E) elementData[--top];
    }
    
    
    @Override
    public E peek() {
        if (isEmpty()) throw new IllegalArgumentException("空栈!");
        return (E) elementData[top-1];
    }
    
    @Override
    public void push(E element) {
        if (isFull()) throw new IllegalArgumentException("栈满!");
        elementData[top++] = element;
    }
    
    @Override
    public boolean isEmpty() {
        return top==0;
    }
    
    @Override
    public boolean isFull() {
        return top==capacity;
    }
    

}
