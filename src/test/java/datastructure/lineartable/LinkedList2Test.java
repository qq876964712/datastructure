package datastructure.lineartable;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedList2Test {

    @Test
    public void test1(){
        LinkedList2<String> list = new LinkedList2<>();
        list.append("11");
        list.insert(0,"22");
        list.insert(2,"33");
        list.append("33");

        System.out.println(list);
        System.out.println(list.remove(3));   ;
        System.out.println(list.remove(0));   ;
        System.out.println(list);
    }
}