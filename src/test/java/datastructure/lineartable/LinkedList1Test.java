package datastructure.lineartable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedList1Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1(){
        LinkedList1<String> list = new LinkedList1<>();
        list.append("11");
        list.insert(0,"22");
        list.insert(2,"33");
        list.append("33");
        System.out.println(list.getHead());
        System.out.println(list.getHead().toReverseString2());
    }
    @Test
    public void test2(){
        LinkedList1<String> list2 = new LinkedList1<>();
        list2.appendByOrder((v1 , v2)->{
            if(v1 ==null  && v2 ==null) return 0;
            if(v1==null) return -1;
            return v1.compareTo(v2);
        },"33","21","11","99","55","77","66");

        LinkedList1<String> list3 = new LinkedList1<>();
        list3.appendByOrder((v1 , v2)->{
            if(v1 ==null  && v2 ==null) return 0;
            if(v1==null) return -1;
            return v1.compareTo(v2);
        },"79","27","36","47","58");
        ;
        System.out.println(list2.getHead());
        System.out.println(list3.getHead());

        LinkedList1.Node<String> theNode = LinkedList1.getTheNode(list3.getHead(), list2.getHead(), (v1, v2) -> {
            if (v1 == null && v2 == null) return 0;
            if (v1 == null) return -1;
            return v1.compareTo(v2);
        });

        System.out.println(theNode);
    }
}