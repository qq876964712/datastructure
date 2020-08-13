package datastructure.lineartable.stackqueue;

public interface Stack<E> {

    E pop();
    void push(E element);
    E peek();
    boolean isEmpty();
    boolean isFull();
}
