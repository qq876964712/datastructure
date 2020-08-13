数据结构学习:
1.可扩容的数组练习 自己实现Array
增强的array 支持 add(i,e)  remove(i)  contains(e) , get(i),set(i,e) 


2.线性表 
子类 :  物理结构 包括  数组和链表

抽象数据结构定义: 数据+关系 +一组操作 (Operation)
包括:
get(int index);
insert(int index ,E element);
append(E element);
set(int index, E element);// 设置第i个元素值为x
remove(int index);
search(Predicate<E> e);返回第一个满足条件的

2.1 模拟util包下的 ArrayList  LinkedList  简单实现 自己的ArrayList以及SingleLinkedList
SortSingleLinkedList
2.2 链表练习   
2.2.1 不带头节点 linkedlist1 
   (1). 包含接口list的部分方法 
    insert(index), append()  appendByorder(Comparator comparator)
    appendByOrderWithHeadNode
   (2). 2个顺序Node n 合并成另一个顺序表
   (3).3种方法Node反转 reverse
   (4)逆序输出 toReverseString
2.2.2
  带头节点 linkedlist2 
2.2.3 简单实现 双向链表带有头节点

2.2.4 单向环形链表 SingleLoopLinkedList 以及约瑟夫问题
    (1)SingLoopLinkedList 包含头节点   并采用head、rear指针 
    主要包含3个方法 insert(index, element) appned(element)  remove(index)
    并实现 2种 josephu 场景
    (2)SingLoopLinkedList2 不包含头节点  采用hear、rear指针
    主要包含3个方法 insert(index, element) appned(element)  remove(index)
    实现 1种 josephu 场景
2.3 特殊的线性表

2.3.1 栈
stack    ArrayStack
1) ArrayStack  pop   push  peek
2) LinkedStack  pop   push  peek

栈的应用   LinkedStackTest RecursionAndStackTest
  数制转换
  计算表达式( 
             中缀表示后缀 中缀表达式转前缀表达式
             不管是后缀还是 前缀 总是从左到右计算  并且先计算靠近第一个数字的运算符(用于辨别优先级的问题)
             换句话说
             后缀 :从左向右遍历 运算符栈顶(较左) ≥ 当前预算符 (较右) 弹出   
             前缀 :从右到左遍历 运算符栈顶(较右)<= 当前运算符 (较左) 继续压栈 
             
            )  中缀表达式转换为 后缀表达式(逆波兰表达式 RPN)  逆波兰表达式的意义: 机器思维无法理解括号的优先级控制
             使用后缀 就可以从左到右遍历表达式,遇到运算符号就弹出2个运算值 ,计算结果   结果入栈,知道 剩下最后一个数字
                 
  括号匹配   
  子方法调用
  递归调用
  深度遍历
  
  
 2.4排序
  常见的排序算法
  冒泡排序 :  o(n^2)
  插入排序 : o(n^2) 最好情况o(n)  适合部分有序
  选择排序 : o(n^2) 
  
  希尔排序 : o(nlogn) 比插入排序和选择排序要快很多 数组越大,优势越大.对于中等大小的数组,使用希尔排序是可以接收的.他的代码量
  不大,也不需要使用额外的内存空间. 对于小规模 的使用插入排序比较好
  归并排序: 适合于处理百万甚至更大规模的数组,主要缺点是 使用的辅助空间
  堆排序 :  利用二叉树进行排序
  快速排序: 快速排序是原地排序,快速排序也是一种分治的思想, 适合随机性比较大的 序列排序,对于小数组  没有插入排序块
  对于随机性好的 序列排序 比希尔排序和 归并排序要好 ,二者存在内部移动数据,而快速排序确定位置 就不动了 ,而且快速排序比较次数较少
  基数排序: 
 2.5查找 
  无序数组  顺序查找
  有序数组  二分查找  mid = (left + right)/2
           插值查找 mid  = left +  (right - left)/(a[right]-a[left]) * (findvalue- a[left])
           斐波那契数列  根据数组长度 得到 对应的fb(k)
 3.map
 主要看视频,学习源码hashmap  concurrenthashmap 1.7 1.8的不同
 4.tree
 4.1二叉树 BinaryTree
 4.1.1二叉树 的遍历
 (1)先序遍历 先序遍历包含3种 遍历方法  递归遍历, 另外2种非递归遍历方法 preOrder1    preOrder2  preOrder3 (类似递归)              
 (2)中序遍历 中序遍历包含2种 遍历方法  递归遍历, 另外1种非递归遍历方法 preOrder1    preOrder2                
 (3)后序遍历 中序遍历包含2种 遍历方法  递归遍历, 另外2种非递归遍历方法 preOrder1    preOrder2(标记根节点的方式)   preOrder3  (非常灵巧的方式)     
 (4)二叉树的层级遍历  二叉树 按照层级打印  
 4.1.2二叉树 的查找

 4.1.3二叉树 根据先序序列 中序序列 构建二叉树(可以根据二叉顺序树ArrayBinaryTree得到先序序列和中序序列 ,然后构建一个 节点形式二叉树)
 4.2 二叉顺序树ArrayBinaryTree 
  实际存储为数组 数组i下标 左子节点下标(2*i+1) 右子节点(2*i+2) 
  简单实现 先序遍历   以及 中序遍历 二叉顺序树 
  完全二叉树 判断二叉树是不是完全二叉树
 4.3 线索二叉树 ThreadedBinaryTree
  为了加快学习进度  只是实现了 前序线索和中序线索 ,线索思路可以直接看该类
 4.4 树的应用
  4.4.1堆排序  ArraySort中heapSort
  4.4.2哈夫曼树  树的带权路径长度最小  最优树
   (二叉树性质  叶子节点个数 = 度为2个节点+1) 所以哈夫曼节点个数 2n-1 个 n为叶子节点个数
   数组存储和链表存储
  收集哈夫曼编码 
  4.4.3二叉排序树 BinarySortTree  add 和remove
  4.4.4平衡二叉树 又称avl树  (由2个人的名字的来的) 主要包括 add  remove  以及寻找后续节点   右旋  左旋    右旋 左旋 左右旋 右左旋 就可以 将不平衡的状态 转换为平衡状态
  可以理解为    根节点 a  左子节点 b  右子树 A  左子节点b的右子树  B  右子节点C
  A的高度 小于 B 的高度  或者 小于 C的高度  经过分析 可以直到  可以通过  右旋 左旋等操作 达到平衡的
  4.4.5红黑树 RBTree  
  相对于严格的平衡二叉树 红黑树使用更加广泛  用于频繁的插入和删除
  红黑树性质 : 
   1)节点非红即黑 2)根节点为黑色 3)节点为红色  他的2个子节点都为黑色  或者都为空
   4)任意一个节点  到以他为根的树上的叶子节点的路径  黑节点数目一样
   5)新添加的节点 颜色是红色
  
  4.5 多叉树  对二叉树的高度
  一个节点可能由多个子节点,而且每个子节点 右可能包含多个数据项
  
   4.5.1 B树  自己实现b树  B_Tree 按照 node.children.key >  node.children.next 和 参考 BTree 但和它不同 
   
  
   
 
  



    



