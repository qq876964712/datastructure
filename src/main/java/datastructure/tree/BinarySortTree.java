package datastructure.tree;

import datastructure.tree.util.TreePrintUtil;

import java.util.function.Consumer;

public class BinarySortTree<E extends Comparable> {
    
    TreeNode<E> root;
    
    public void add(E data){
        if(data==null)return;
        if(root==null){
           root = new TreeNode<>(data);
        }else{
            TreeNode<E> p =root;
            for(;;){
              if(data.compareTo(p.data)>0 )  {
                  if (p.rchild == null) {
                      p.rchild = new TreeNode<>(data,p);
                      break;
                  }
                  p= p.rchild;

              }else{
                  if(p.lchild == null){
                      p.lchild = new TreeNode<>(data,p);
                      break;
                  }
                  p = p.lchild;
              }
            }
        }
    }
    public void remove(E data){
        if(data==null || root == null)return;
        TreeNode<E> p = root;
        for(;;){
            if(p == null || data.compareTo(p.data) == 0)break;
            if(data.compareTo(p.data)>0){
                p =p.rchild;
            }
            if(data.compareTo(p.data)<0){
                p = p.lchild;
            }
        }
        if(p==null) return; //没找到
        if(p.lchild==null && p.rchild==null){   //被删除节点没有子节点
            if(p==root) root=null;
            else{
                if(p.parent.lchild==p){  //判断被删除节点是父节点的 左孩子还是右孩子
                    p.parent.lchild =null;
                }else{
                    p.parent.rchild = null;
                }
            }
        }else if(p.lchild == null || p.rchild == null){ //被删除节点只有一个子节点
            if(p==root){
                if(p.lchild == null){
                    root = p.rchild;
                }else{
                    root = p.lchild;
                }
                root.parent = null;
            }else{
                if (p.parent.lchild == p) {
                    if (p.lchild == null) {
                        p.parent.lchild = p.rchild;
                        p.rchild.parent = p.parent;
                    } else {
                        p.parent.lchild = p.lchild;
                        p.lchild.parent = p.parent;
                    }
                } else {
                    if (p.lchild == null) {
                        p.parent.rchild = p.rchild;
                        p.rchild.parent = p.parent;
                    } else {
                        p.parent.rchild = p.lchild;
                        p.lchild.parent = p.parent;
                    }
                }
            }
        }else{//被删除节点有2个子节点   找到 被删除节点的 右子树的
            //把原节点的data 替换为右子树的最小 的数据 删除 右子树的最小数据
            TreeNode<E> minChildNode = getMinChildNode(p.rchild);
            remove(minChildNode.data);
            p.data  = minChildNode.data;
        }
    }
    
    public void inOrderConsumer(Consumer<E> consumer) {
        if(root==null)return;
        root.inOrder(consumer);
    }
    
    public TreeNode<E> getMinChildNode(TreeNode<E> node){
        while(node.lchild!=null){
            node = node.lchild;
        }
        return node;
    }
    
    public TreeNode<E> getSuccessor(TreeNode<E> node){
        if(node==null)return null;
        TreeNode<E> rnode = node.rchild;
        if(rnode!=null){
            while(rnode.lchild!=null){
                rnode = rnode.lchild;
            }
            return rnode;
        }else{
            TreeNode<E> parent =node.parent;
            for(; parent!=null && node==parent.rchild;){
                node= parent;
                parent= parent.parent;
            }
            return parent;
        }
    }
    
    public void print(){
        TreePrintUtil.pirnt(root);
    }
    
    class TreeNode<E> implements TreePrintUtil.TreeNode {
        E data;
        private TreeNode lchild,rchild;
        private TreeNode parent;
    
        public TreeNode() {
        }
    
        public TreeNode(E data) {
            this.data = data;
        }
    
        public TreeNode(E data, TreeNode parent) {
            this.data = data;
            this.parent = parent;
        }
        public void inOrder(Consumer<E> consumer){
            if(lchild!=null){
                lchild.inOrder(consumer);
            }
            consumer.accept(data);
            if (rchild != null) {
                rchild.inOrder(consumer);
            }
        }
    
        public String toString() {
            return "[" + data + "]";
        }
    
        @Override
        public String getPrintInfo() {
            return "["+data+"]";
        }
    
        @Override
        public TreePrintUtil.TreeNode getLeftChild() {
            return lchild;
        }
    
        @Override
        public TreePrintUtil.TreeNode getRightChild() {
            return rchild;
        }
    }
    
    public static void main(String[] args) {
        BinarySortTree<String> tree = new BinarySortTree<>();
        tree.add("E");
        tree.add("B");
        tree.add("C");
        tree.add("F");
        tree.add("G");
        tree.add("A");

        tree.print();
        tree.remove("B");
        tree.remove("A");

        tree.print();
    }
}
