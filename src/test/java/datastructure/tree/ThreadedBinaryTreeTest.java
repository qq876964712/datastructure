package datastructure.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadedBinaryTreeTest {
    @Test
    public void test1(){
    
        String arr[] = new String[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "" + (i + 1);
        }
        ThreadedBinaryTree<String> tree = ThreadedBinaryTree.createTree(arr);
        tree.inThreadOrder();
        tree.inthreadedNextOrder();
    }
}