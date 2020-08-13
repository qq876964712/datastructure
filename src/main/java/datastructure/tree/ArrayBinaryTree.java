package datastructure.tree;

import datastructure.lineartable.stackqueue.LinkedStack;
import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

public class ArrayBinaryTree<E> {
    private Object[]arr;
    
    public ArrayBinaryTree(E[] arr){
        this.arr =arr;
    }
    
    
    public void preOrder(Consumer<E> consumer){
        LinkedStack<E> stack = new LinkedStack<>();
        if(arr==null)return ;
        LinkedStack<Integer> indexStack = new LinkedStack<>();
        indexStack.push(0);
        stack.push((E)arr[0]);
        for(;!stack.isEmpty();){
            E pop = stack.pop();
            consumer.accept(pop);
            Integer index = indexStack.pop();
            if(2* index+2<arr.length && arr[2*index+2]!=null){
                stack.push((E)arr[2*index+2]);
                indexStack.push(2*index+2);
            }
            if (2 * index+1  < arr.length && arr[2 * index+1] != null ) {
                stack.push((E) arr[2 * index+1]);
                indexStack.push(2 * index+1);
            }
        }
    }
    public void inOrder(Consumer<E> consumer){
        LinkedStack<E> stack = new LinkedStack<>();
        HashMap<E, Integer> indexmaps = new HashMap<>();
        E p = (E)arr[0];
        indexmaps.put(p,0);
        for(;p!=null || !stack.isEmpty();){
            if(p!=null){
                Integer integer = indexmaps.get(p);
                stack.push(p);
                if(integer*2+1>=0  && integer*2+1<arr.length){
                    p =(E) arr[2*integer+1];
                    indexmaps.put(p,2*integer+1);
                }else{
                    p=null;
                }
            }else{
                p =stack.pop();
                consumer.accept(p);
                Integer integer =indexmaps.get(p);
                if (integer * 2+2 >= 0 && integer * 2 +2<arr.length) {
                    p = (E) arr[2 * integer+2];
                    indexmaps.put(p, 2 * integer+2);
                } else {
                    p = null;
                }
            }
        }
    }
    
    public void postOrder(Consumer<E> consumer) {
        postOrder(consumer,0);
    }
    private void postOrder(Consumer<E> consumer,int index){
        if(index<arr.length){
            postOrder(consumer,index*2+1);
            postOrder(consumer,index*2+2);
            E p = (E)arr[index];
            consumer.accept(p);
        }
    }

    
}
