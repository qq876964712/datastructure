package datastructure.tree;

import datastructure.tree.util.TreePrintUtil;

/**
 *
 * 平衡二叉树失去平衡的 基本情况       基本都可以通过这样一次左旋 或者右旋来达到平衡
 *
 *                gparent                       parent
 *                  /   \                        /  \
 *            (h-1)[c]  parent    旋转后     gparent  (h)[a]
 *                     /   \                /    \
 *                    (h)[b]  (h)[a]      (h-1)[c] (h)[b]
 *  添加节点时 {
 *   [a] [b]不可能同时  比  [c]高2个高度
 *   如果 [a] -[c] =2 一次gparent 旋转
 *   如果 [b] -[c] =2 一次parent 旋转 将[b]旋转到[a]
 *  }
 *  删除节点时{
 *    如果[a] [b] 可以同时 比 [c]高2个高度 直接旋转 同 [a]-[c] =2 时一样
 *    如果 [a] -[c] =2 一次gparent 旋转
 *    如果 [b] -[c] =2 一次parent 旋转 将[b]旋转到[a]
 *  }
 */
public class AVLTree {
    
    public Node root;

    public void  add(String e){
        if(e==null)return;
        if(root==null) root = new Node(e);
        else{
        
      
        Node p =root;
        for(;;){
            if(e.compareTo(p.data) >0){
                if(p.rchild ==null){
                   p.rchild = new Node(e,p);
                   break;
                }
                p=p.rchild;
            }else{
                if(p.lchild ==null){
                    p.lchild = new Node(e, p);
                    break;
                }
                p=p.lchild;
            }
        }
       
        //添加完整后 进行调整
        rebuild(p);
        }
        
    }
    
    public void remove(String s){
        if(root==null || s==null)return ;
        Node p = root;
        for(;p!=null;){
            if(s.compareTo(p.data) ==0 )
                break;
            else if(s.compareTo(p.data) >0){
                p= p.rchild;
            }else{
                p = p.lchild;
            }
        }
        if(p==null)return;//没找到
        if(p.rchild ==null   &&  p.lchild ==null){
            if(p==root){
                root =null;
            }else{
                if(p.parent.lchild == p){
                    p.parent.lchild =null;
                }else{
                    p.parent.rchild = null;
                }
            }
        }else if(p.rchild == null || p.lchild == null){
            if(p==root){
                if(p.rchild ==null){
                    root = p.lchild;
                }else{
                    root = p.rchild;
                }
                root.parent = null;
            }else{
                if (p.rchild == null) {
                    p.lchild.parent = p.parent;
                    if (p.parent.rchild == p) {
                        p.parent.rchild = p.lchild;
                    }else {
                        p.parent.lchild = p.lchild;
                    }
                }else {
                    p.rchild.parent = p.parent;
                    if (p.parent.rchild == p) {
                        p.parent.rchild = p.rchild;
                    } else {
                        p.parent.lchild = p.rchild;
                    }
                }
            }
        }else{
            Node deleteNode = getSuccessor(p);
            remove(deleteNode.data);
            p.data = deleteNode.data;
        }
        rebuild(p.parent);
    }
    
    public Node getSuccessor(Node node) {
        if (node == null) return null;
        Node rnode = node.rchild;
        if (rnode != null) {
            while (rnode.lchild != null) {
                rnode = rnode.lchild;
            }
            return rnode;
        } else {
            Node parent = node.parent;
            for (; parent != null && node == parent.rchild; ) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }
    
    private void rebuild(Node node){
        while(node!=null){
            if(calNodeBalanceFactor(node)==2){
                doBalance(node,1);
            }else if(calNodeBalanceFactor(node) == -2){
                doBalance(node, 2);
            }
            node=node.parent;
        }
    }
    private int calNodeBalanceFactor(Node p){
        if(p==null)return 0;
        return getHeight(p.lchild)-getHeight(p.rchild);
    }
    
    private int  getHeight(Node e){
        if(e==null)return 0;
        return 1+ Math.max(getHeight(e.lchild),getHeight(e.rchild) );
    }
    private void doBalance(Node anode,int type){
        if(type==1){
            Node bnode = anode.lchild;
            if(bnode.rchild !=null && bnode.lchild==null){
                leftRotation(bnode);
                rightRotation(anode);
            }else if(bnode.lchild !=null){
                rightRotation(anode);
            }else{
            
            }
        }else if(type==2){
            Node bnode = anode.rchild;
            if(bnode.lchild !=null && bnode.rchild==null){
                rightRotation(bnode);
                leftRotation(anode);
            }else if(bnode.rchild !=null){
                leftRotation(anode);
            }
        }
    
    }
    //左旋转
    private Node leftRotation(Node anode){
        if(anode==null)return null;
        Node bnode = anode.rchild;
        anode.rchild =bnode.lchild;
        if(anode.rchild !=null){
            anode.rchild.parent =anode;
        }
        bnode.parent =anode.parent;
        if(anode.parent==null){
            root = bnode;
        }else if(anode.parent.rchild ==anode){
            anode.parent.rchild =bnode;
        }else{
            anode.parent.lchild = bnode;
        }
        anode.parent = bnode;
        bnode.lchild = anode;
        return bnode;
    }
    
    private Node rightRotation(Node anode) {
        if(anode==null)return null;
        Node bnode = anode.lchild;
        anode.lchild = bnode.rchild;
        if(anode.lchild !=null){
            anode.lchild.parent= anode;
        }
        bnode.parent =anode.parent;
        if(anode.parent==null){
            this.root = bnode;
        }else if(anode.parent.rchild == anode){
            anode.parent.rchild = bnode;
        }else{
            anode.parent.lchild = bnode;
        }
        
        anode.parent =bnode;
        bnode.rchild = anode;
        
        return bnode;
    }
    public void print(){
        TreePrintUtil.pirnt(root);
    }
    
    class  Node  implements TreePrintUtil.TreeNode {
        String data;
        Node lchild, rchild;
        Node parent;
    
        public Node(String data) {
            this.data = data;
        }
    
        public Node(String data, Node parent) {
            this.data = data;
            this.parent = parent;
        }
    
    
        @Override
        public String getPrintInfo() {
            return "["+ data + "]";
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
}
