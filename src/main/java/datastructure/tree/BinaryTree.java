package datastructure.tree;

import datastructure.lineartable.stackqueue.LinkedStack;

import java.net.BindException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BinaryTree<E> {
    private TreeNode<E> root;
    
    private  BinaryTree(){
    
    }
    
    public static <E> BinaryTree<E> createTree(E[] arr){
        BinaryTree<E> tree = new BinaryTree<>();
        tree.root =createNode(arr, 0);
        return tree;
    }
    
    private static <E> TreeNode<E> createNode(E[]arr , int  index){
        if(index<arr.length){
            TreeNode<E> eTreeNode = new TreeNode<>(arr[index]);
            eTreeNode.left = createNode(arr,2*index+1);
            eTreeNode.right = createNode(arr, 2 * index+2);
            return eTreeNode;
        }
        return null;
    }
    public static <E> BinaryTree<E> createTree(E[] preOrder, E[] inOrder){
        TreeNode<E> node = createNode(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
        BinaryTree<E> eBinaryTree = new BinaryTree<>();
        eBinaryTree.root=node;
        return eBinaryTree;
    }
    
    private static <E> TreeNode<E> createNode(E[] preOrder, E[] inOrder,int startPreIndex,int endPreIndex,int startInIndex,int endInIndex) {
        TreeNode<E> eTreeNode =null;
        if(startPreIndex >endPreIndex || startInIndex > endInIndex)return null;
        
        E  theRootData = preOrder[startPreIndex];
        int i = startInIndex;
        for( ;i<inOrder.length && i<=endInIndex;i++){
            if(theRootData.equals(inOrder[i]))break;
        }
        if(i>endInIndex) return null;
        //此时i为中序遍历的根节点  根节点左边是他的左子树  右边是他的右子树
        eTreeNode = new TreeNode<>(theRootData);
        eTreeNode.left = createNode(preOrder,inOrder,startPreIndex+1, i - startInIndex+startPreIndex , startInIndex, i-1);
        eTreeNode.right = createNode(preOrder, inOrder, i - startInIndex + startPreIndex+1, endPreIndex,i+1 , endInIndex);
    
        return eTreeNode;
    }

    public void preOrder(Consumer<E> consumer){
        preOrder(root,consumer);
    }
    public void preOrder(TreeNode<E> node,Consumer<E> consumer){
        if(node!=null){
            consumer.accept(node.data);
            preOrder(node.left,consumer);
            preOrder(node.right,consumer);
        }
    }
    public void inOrder(Consumer<E> consumer){
        inOrder(root,consumer);
    }
    
    public void inOrder(TreeNode<E> node,Consumer<E> consumer) {
        if(node!=null){
            inOrder(node.left,consumer);
            consumer.accept(node.data);
            inOrder(node.right, consumer);
        }
    }
    
    public void postOrder(Consumer<E> consumer) {
        postOrder(root, consumer);
    }
    /**   1
     2               3
     4       5       6       7
     8   9   10   11   12   13   14   15
        */
    
    public void postOrder(TreeNode<E> node, Consumer<E> consumer) {
        if (node != null) {
            postOrder(node.left, consumer);
            postOrder(node.right, consumer);
            consumer.accept(node.data);
        }
    }
    public void levelOrder(Consumer<E> consumer){
        LinkedList<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode<E> p;
        for(;(p=queue.pop())!=null;){
            consumer.accept(p.data);
            if(p.left!=null){
                queue.offer(p.left);
            }
            if(p.right!=null){
                queue.offer(p.right);
            }
        }
    
    }
    public void printLevelOrder(){
        LinkedList<TreeNode<E>> parentQueue = new LinkedList<>();
        LinkedList<TreeNode<E>> childQueue = new LinkedList<>();
        childQueue.offer(root);
        TreeNode<E> p,c;
        int height = getHeight();
        int i = 0;
        for (; !parentQueue.isEmpty() || !childQueue.isEmpty() ; ) {
            if(i> height)break;
            if(!parentQueue.isEmpty()){
                p = parentQueue.poll();
                System.out.print(p.data==null ? " ": p.data  +"");
                int rowhalf = (int) Math.pow(2,height-i+2);
                for(int tmp= 1;tmp<rowhalf;tmp++){
                    System.out.print(" ");
                }
                if (p.left != null) {
                    childQueue.offer(p.left);
                } else {
                    childQueue.offer(new TreeNode<E>(null));
                }
                if (p.right != null) {
                    childQueue.offer(p.right);
                } else {
                    childQueue.offer(new TreeNode<E>(null));
                }
            }else{
                if(i!=0)System.out.println();
                i++;

                for(int tmp = 0; tmp<=3* Math.pow(2,height-i); tmp++){
                    System.out.print(" ");
                }
                LinkedList<TreeNode<E>> tmp = parentQueue;
                parentQueue = childQueue;
                childQueue =tmp;
            }
        }
        
        
    }
    
    public void preOrder2(Consumer<E> consumer) {
        LinkedStack<TreeNode<E>> stack = new LinkedStack<>();
        TreeNode<E> p = root;
        for(;p!=null || !stack.isEmpty();){
            
            if(p!=null){
                consumer.accept(p.data);
                stack.push(p);
                p = p.left;
            }else{
                TreeNode<E> pop = stack.pop();
                p=pop.right;
            }
        }
    }

    public void inOrder2(Consumer<E>consumer){
        LinkedStack<TreeNode<E>> stack = new LinkedStack<>();
        TreeNode<E> p = root;
        
        for(; p!=null || !stack.isEmpty() ;){
            if(p!=null){
                stack.push(p);
                p = p.left;
            }else{
                TreeNode<E> pop = stack.pop();
                consumer.accept(pop.data);
                p = pop.right;
            }
        }
    }

    private static final int left = 0;
    private static final int right = 1;
    public void postOrder2(Consumer<E> consumer) {
        LinkedStack<TreeNode<E>> stack = new LinkedStack<>();
        LinkedStack<Integer> stack2 = new LinkedStack<>();
    
        TreeNode<E> p = root;
        
        for (; p != null || !stack.isEmpty(); ) {
            if(p!=null){
                stack.push(p);
                stack2.push(left);
                p = p.left;
            }else{
                if(stack2.peek() == left){
                    stack2.pop();
                    stack2.push(right);
                    p = stack.peek().right;
                }else if(stack2.peek() == right){
                    stack2.pop();
                    TreeNode<E> pop = stack.pop();
                    consumer.accept(pop.data);
                }
            }
        }
    }
    
    //根左右
    public void preOrder3(Consumer<E> consumer) {
        LinkedStack<TreeNode<E>> stack = new LinkedStack<>();
        if (root == null) return;
        stack.push(root);
        for (; !stack.isEmpty(); ) {
            TreeNode<E> pop = stack.pop();
            consumer.accept(pop.data);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }
    
//左根右  通过这种方式写不来
    
    //左右根  根右左的逆置
    public void postOrder3(Consumer<E> consumer) {
        LinkedStack<TreeNode<E>> stack = new LinkedStack<>();
        LinkedList<TreeNode<E>> list = new LinkedList<>();
        if(root==null)return;
        stack.push(root);
        for(;!stack.isEmpty();){
            TreeNode<E> pop = stack.pop();
            list.add(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        Collections.reverse(list);
        list.forEach(e->{
            consumer.accept(e.data);
        });
        
    }
    
    public E search(Predicate<E> predicate){
        if(root==null)return null;
        //以先序遍历查找
        LinkedStack<TreeNode<E>> stack = new LinkedStack<>();
        stack.push(root);
        for(;!stack.isEmpty();){
            TreeNode<E> pop = stack.pop();
            if(predicate.test(pop.data))return pop.data;
            if(pop.right!=null){
                stack.push(pop.right);
            }
            if(pop.left!=null){
                stack.push(pop.left);
            }
        }
        return null;
    }
    
    public int getHeight(){
        return getHeight(root);
    }
    public int getHeight(TreeNode<E> node){
        if(node==null) return 0;
        
        int lh = 1+ getHeight(node.left);
        int rh = 1 + getHeight(node.right);
        return Math.max(lh, rh);
    }
    
    /**
     *层级遍历整个树
     * 如果遇到一个节点如果这个节点没有子节点  那么之后的所有节点都不会有子节点
     *              如果有左子节点 无右子节点 之后所有节点都不会有子节点
     *              如果无左子节点 有右节点  他肯定不是完全二叉树
     *
     */
    public boolean isCompleteTree(){
        LinkedList<TreeNode<E>> levelqueue = new LinkedList<>();
        TreeNode<E> p =root;
        levelqueue.offer(p);
        int tag =0;
        for(;!levelqueue.isEmpty()&& tag ==0;){
            TreeNode<E> poll = levelqueue.poll();
            if(poll.left ==null && poll.right==null){
                tag =1;
            }else if(poll.left!=null && poll.right ==null){
                levelqueue.offer(poll.left);
                tag =1;
            }else if(poll.left==null && poll.right!=null){
                return false;
            }else{
                levelqueue.offer(poll.left);
                levelqueue.offer(poll.right);
            }
        }
        
        for(; !levelqueue.isEmpty();){
            TreeNode<E> poll = levelqueue.poll();
            if(poll.left!=null || poll.right!=null){
                return false;
            }
        }
        return true;
    }
    static class TreeNode<E>{
        E data;
        private TreeNode<E> right, left;
    
        public TreeNode() {
            this(null, null, null);
        }
    
        public TreeNode(E data) {
            this(data,null,null);
        }
    
        public TreeNode(E data, TreeNode<E> right, TreeNode<E> left) {
            this.data = data;
            this.right = right;
            this.left = left;
        }
    }
}
