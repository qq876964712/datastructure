package datastructure.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    @Test
    public void test1(){
        String[] preOrder = {"1", "2", "4", "8", "9", "5", "10", "11", "3", "6", "12", "13", "7", "14", "15"};
        String[] inOrder = {"8", "4", "9", "2", "10", "5", "11", "1", "12", "6", "13", "3", "14", "7", "15"};
        //由二叉顺序树
        BinaryTree<String> tree = BinaryTree.createTree(preOrder, inOrder);
        tree.printLevelOrder();
    }
    
    @Test
    public void test2() {
        String arr[] = new String[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "" + (i + 1);
        }
        BinaryTree<String> tree = BinaryTree.createTree(arr);
        tree.printLevelOrder();
    }
    
    @Test
    public void testpreorder() {
        String arr[] = new String[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "" + (i + 1);
        }
    
        final StringBuffer buffer = new StringBuffer();
    
        BinaryTree<String> tree = BinaryTree.createTree(arr);
        tree.preOrder(v->{
            if (v != null) buffer.append("\"" + v + "\",");
        });
        buffer.append("\n");
        tree.preOrder2(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
        buffer.append("\n");
        tree.preOrder3(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
        System.out.println(buffer.toString());
    
    }
    
    @Test
    public void inorder() {
        String arr[] = new String[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "" + (i + 1);
        }
        final StringBuffer buffer = new StringBuffer();
    
        BinaryTree<String> tree = BinaryTree.createTree(arr);
        tree.inOrder(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
        buffer.append("\n");
        tree.inOrder2(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
        System.out.println(buffer.toString());
    }
    
    @Test
    public void postorder() {
        String arr[] = new String[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "" + (i + 1);
        }
        final StringBuffer buffer = new StringBuffer();
    
        BinaryTree<String> tree = BinaryTree.createTree(arr);
        tree.postOrder(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
        buffer.append("\n");
        tree.postOrder2(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
        buffer.append("\n");
        tree.postOrder3(v -> {
            if (v != null) buffer.append("\"" + v + "\",");
        });
        System.out.println(buffer);
    }
}