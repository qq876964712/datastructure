package datastructure.lineartable;

import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleLinkedListTest {

    @Test
    public void test1(){
        DoubleLinkedList<String> list = new DoubleLinkedList<String>();
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