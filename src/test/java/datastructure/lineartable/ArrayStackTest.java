package datastructure.lineartable;

import datastructure.lineartable.stackqueue.ArrayStack;
import org.junit.Test;

public class ArrayStackTest {
    
    @Test
    public void test(){
        ArrayStack<String> s = new ArrayStack<>(1);
        s.push("11");
        System.out.println(s.pop());
        s.push("22");
        System.out.println(s.peek());
    }
    

}