package datastructure.tree;

import datastructure.lineartable.stackqueue.LinkedStack;

/**
 * 线索二叉树 的节点 包括ltag ,rtag2个标志位  标志位:0  表示左(右)节点上表示为左(右)子节点; 1 表示前(后)驱节点
 * 线索化的过程 实际就是遍历的过程  存在2个指针 当前节点 p 以及 当前节点的前一个节点 prior
 * 如果当前节点的 lchild ==null  则将 p.ltag =1 p.ltag = prior
 * 如果前一个节点的 rchild == null 则将prior.rtag =1 ;prior.rchild = p;
 *
 *
 * 线索化后的二叉树 为线索二叉树
 * 对于中序线索化的二叉树
 * 对于任意一个节点p 他的前驱previous    if p.ltag=1  previous=p.lchild   else  左子树的最后一个节点
 *                他的后继next        if p.rtag =1 next    =p.rchild   else 右子树的第一个节点
 * 对于前序线索化二叉树
 * 对于任意一个节点p 他的前驱previous   if p.ltag =1 previous = p.lchid else 后一个兄弟
 *                他的后继next      if p.lag ==
 */
public class ThreadedBinaryTree<E> {
    
    private ThreadedBinaryNode<E> root;
    
    public static <E> ThreadedBinaryTree<E> createTree(E[] arr) {
        ThreadedBinaryTree<E> tree = new ThreadedBinaryTree<>();
        tree.root = createNode(arr, 0);
        return tree;
    }
    
    private static <E> ThreadedBinaryNode<E> createNode(E[] arr, int index) {
        if (index < arr.length) {
            ThreadedBinaryNode<E> eTreeNode = new ThreadedBinaryNode<>(arr[index]);
            eTreeNode.lchild = createNode(arr, 2 * index+1);
            eTreeNode.rchild = createNode(arr, 2 * index+2);
            return eTreeNode;
        }
        return null;
    }
    public static <E> ThreadedBinaryTree<E> createTree(E[] preOrder, E[] inOrder) {
        ThreadedBinaryNode<E> node = createNode(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
        ThreadedBinaryTree<E> eBinaryTree = new ThreadedBinaryTree<>();
        eBinaryTree.root = node;
        return eBinaryTree;
    }
    
    private static <E> ThreadedBinaryNode<E> createNode(E[] preOrder, E[] inOrder, int startPreIndex, int endPreIndex, int startInIndex, int endInIndex) {
        ThreadedBinaryNode<E> eTreeNode = null;
        if (startPreIndex > endPreIndex || startInIndex > endInIndex) return null;
        
        E theRootData = preOrder[startPreIndex];
        int i = startInIndex;
        for (; i < inOrder.length && i <= endInIndex; i++) {
            if (theRootData.equals(inOrder[i])) break;
        }
        if (i > endInIndex) return null;
        //此时i为中序遍历的根节点  根节点左边是他的左子树  右边是他的右子树
        eTreeNode = new ThreadedBinaryNode<>(theRootData);
        eTreeNode.lchild = createNode(preOrder, inOrder, startPreIndex + 1, i - startInIndex + startPreIndex, startInIndex, i - 1);
        eTreeNode.rchild = createNode(preOrder, inOrder, i - startInIndex + startPreIndex + 1, endPreIndex, i + 1, endInIndex);
        
        return eTreeNode;
    }
    
    
    public void inThreadOrder(){
        inThreadOrder(root);
    }
    private ThreadedBinaryNode<E> prior =null;
    public void inThreadOrder(ThreadedBinaryNode<E> node){
        if(node==null)return;
        inThreadOrder(node.lchild);
        if(node.lchild==null){
            node.ltag =1;
            node.lchild = prior;
        }
        if(prior!=null && prior.rchild==null){
            prior.rtag = 1;
            prior.rchild = node;
        }
        prior =node;
        inThreadOrder(node.rchild);
    }
    public void inthreadedNextOrder(){
        
        ThreadedBinaryNode<E> p=root;
        while(p.lchild!=null) p=p.lchild;
        System.out.println(p.data);
        while( (p= inNext(p))!=null){
            System.out.println(p.data);
        }
    }
    public ThreadedBinaryNode<E> inNext(ThreadedBinaryNode<E> node){
        if(node.rtag ==1){
            node = node.rchild;
        }else{
            node = node.rchild;
            for(;node!=null && node.ltag ==0;){
                node = node.lchild;
            }
        }
        return node;
    }
    
    public ThreadedBinaryNode<E> inPrevious(ThreadedBinaryNode<E> node) {
        if(node.ltag==1){
            node =node.lchild;
        }else{
            node= node.lchild;
            for(;node!=null && node.rtag==0;){
                node =node.rchild;
            }
        }
        return node;
    }
    
    
    
    
    public void preThreadOrder(){
        LinkedStack<ThreadedBinaryNode> stack =new LinkedStack<>();
        stack.push(root);
        ThreadedBinaryNode<E> prior = null;
        for(;!stack.isEmpty();){
            ThreadedBinaryNode pop = stack.pop();
            
            //前驱
            if(pop.lchild==null){
                pop.ltag=1;
                pop.lchild = prior;
            }
            //后继
            if(prior!=null && prior.rchild ==null){
                prior.rtag =1;
                prior.rchild = pop;
            }
            if(pop.rchild!=null && pop.rtag==0){
                stack.push(pop.rchild);
            }
            if(pop.lchild!=null&& pop.ltag==0){
                stack.push(pop.lchild);
            }
            prior = pop;
        }
    }
    
    public ThreadedBinaryNode<E> preNext(ThreadedBinaryNode<E> node) {
    
//        if (node.rtag==1) {
//            node = node.rchild;
//        } else if(node.ltag==0 && node.lchild!=null) {
//            node = node.lchild;
//        }else if(node.rchild!=null){
//            node = node.rchild;
//        }else{
//            node =null;
//        }
        if(node.ltag==0 && node.lchild!=null){
            node = node.lchild;
        }else{
            node = node.rchild;
        }
        return node;
    }
    @Deprecated
    public ThreadedBinaryNode<E> prePrevious(ThreadedBinaryNode<E> node) {
        if (node.ltag == 1) {
            node = node.lchild;
        } else {
            //如果 该节点为 他父节点的左子节点   他的前驱 就是该父节点 否则是 该父节点的左子树的最后一个节点
            
        }
        return node;
    }
    
    
    
    static class ThreadedBinaryNode<E>{
        E data;
        private ThreadedBinaryNode<E> lchild ,rchild;
        private int ltag,rtag;
    
        public ThreadedBinaryNode(E data, ThreadedBinaryNode<E> lchild, ThreadedBinaryNode<E> rchild, int ltag, int rtag) {
            this.data = data;
            this.lchild = lchild;
            this.rchild = rchild;
            this.ltag = ltag;
            this.rtag = rtag;
        }
    
        public ThreadedBinaryNode(E data) {
            this(data,null,null,0,0);
        }
    }
}
