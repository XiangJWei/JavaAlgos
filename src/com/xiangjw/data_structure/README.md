### 数据结构部分（data_structure包）
   1. array数组
      + CustomArray封装基础数组，以及常用操作。
      + CustomArray类中有一个常用算法场景，就是如何判断该数组内容是否为回型串（如12321）。
      + DynamicArray封装动态数组，引入泛型和动态扩容的功能，类似定义了一个简单的ArrayList。
   2. linkedlist链表
      + CustomLinkedList封装基础链表，实现其常用操作。
      + CustomLinkedList类中有一个常用算法场景，就是如何判断该数组内容是否为回型串（如12321）。其实现方式比数组复杂。
      + LinkedListAlgo封装链表常用算法类，实现几种链表的常用场景。
         - 引入了哨兵节点；
         - 实现单链表反转；
         - 链表中环的检测；
         - 两个有序的链表合并；
         - 删除链表倒数第 n 个结点；
         - 求链表的中间结点；
      + SkipLinkedList跳表，利用链表实现插入删除查找都还比较快的一种方式。
   3. stack栈
      + ArrayStack利用数组实现栈的功能，封装常用入栈出栈操作，可动态扩容。
      + LinkedlistStack利用链表实现栈的功能，封装常用入栈出栈操作。
      + Brower是一个常用的算法场景，利用栈实现一个建议的浏览器前进后退的功能。
      + Calculator也是一个常用的算法场景，利用栈实现一个建议计算，比如计算(3+5.2)/8-6/3的结果。
   4. queue队列
      + ArrayQuene利用简单数组实现队列功能，动态扩容，存在内存浪费。
      + ArrayCircleQueue利用数组实现一个容量固定的循环队列。
      + LinkedListQueue利用链表实现一个队列。
   5. tree二叉树
      + CustomTree定义一个基础的二叉树，并实现了如下常用的算法场景。
         - 前序遍历；
         - 中序遍历；
         - 后序遍历；
         - 按层次遍历（重难点）。
      + BinarySearchTree二叉查找树，其中序遍历出来就是有序的，常用于数据排序（如红黑树）；
      + TrieTree，常用于寻找前缀匹配的字符串，如搜索引擎关键字检索。
   6. heap堆
      + CustomHeap实现了一个大顶堆，并封装其常用操作。常用于查找topN等（~~后续会实现其算法场景~~）。
   7. graph图
      + CustomGraph基于数组加链表实现一个无向图，并实现了如下常用了算法场景。
         - 深度优先搜索；
         - 广度优先搜索。
   8. hash散列表
      + CustomHashTable封装简单的hashmap，实现常用put、get、remove操作。含数组、链表、散列表、跳表的插入查找性能对比。

---
END