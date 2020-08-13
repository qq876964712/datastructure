package datastructure.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayBinaryTreeTest {
    @Test
    public void test1(){
        String arr[] = new String[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "" + (i + 1);
        }
    
        final StringBuffer buffer = new StringBuffer();
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<String>(arr);
        tree.preOrder(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
    
        final StringBuffer buffer2 = new StringBuffer();
        tree.inOrder(v -> {
            if (v != null) buffer2.append("\"" + v + "\",");
        });
        System.out.println(buffer);
        System.out.println();
        System.out.println(buffer2);
    }
}