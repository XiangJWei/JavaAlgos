# Java算法实例
JAVA实现的各种数据结构和常用算法代码样例，并包含部分性能对比
- 本例采用eclipse作为IDE，UTF-8编码，每个类都可独立Run Application体验实际运行效果和性能对比
- 本例为学习数据结构和算法过程中编写的示例代码，为加深印象，供参考。大神可绕道，不足之处还请指正，互相学习，感谢支持~
- doc目录下存放的程序猿必备神器，如阿里巴巴代码规范

> 文中图片素材来源于[极客邦](https://www.geekbang.org/ "极客邦")

### 具体内容如下
以下按照难度的由简入繁进行排序，新手建议按照下面的顺序进行研究；
如下只是目录简要说明，具体类里有较详细的说明和注释，供详细研究。

---
1. JAVA基础部分（java_base包）
   1. DataTest
      + 打印常用基础数据类型的字节大小、最大值和最小值。
      + 对比逻辑运算乘2运算和位运算乘2运算的时间效率。
      + 对比基础int和Integer封装类运算的时间效率。
2. 数据结构部分（data_structure包）
   1. array数组
      + CustomArray封装基础数组，以及常用操作。
      + CustomArray类中有一个常用算法场景，就是如何判断该数组内容是否为回型串（如12321）。
      + DynamicArray封装动态数组，引入泛型和动态扩容的功能，类似定义了一个简单的ArrayList。
![数组](http://www.xiangjw.tk/res/img/1.jpg "数组")
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
![链表 vs 数组](http://www.xiangjw.tk/res/img/2.jpg "链表 vs 数组")
![跳表](http://www.xiangjw.tk/res/img/3.jpg "跳表")
   3. stack栈
      + ArrayStack利用数组实现栈的功能，封装常用入栈出栈操作，可动态扩容。
      + LinkedlistStack利用链表实现栈的功能，封装常用入栈出栈操作。
      + Brower是一个常用的算法场景，利用栈实现一个建议的浏览器前进后退的功能。
      + Calculator也是一个常用的算法场景，利用栈实现一个建议计算，比如计算(3+5.2)/8-6/3的结果。
![栈](http://www.xiangjw.tk/res/img/4.jpg "栈")
   4. queue队列
      + ArrayQuene利用简单数组实现队列功能，动态扩容，存在内存浪费。
      + ArrayCircleQueue利用数组实现一个容量固定的循环队列。
      + LinkedListQueue利用链表实现一个队列。
![队列 vs 栈](http://www.xiangjw.tk/res/img/5.jpg "队列 vs 栈")
   5. tree二叉树
      + CustomTree定义一个基础的二叉树，并实现了如下常用的算法场景。
         - 前序遍历；
         - 中序遍历；
         - 后序遍历；
         - 按层次遍历（重难点）。
      + BinarySearchTree二叉查找树，其中序遍历出来就是有序的，常用于数据排序（如红黑树）；
      + TrieTree，常用于寻找前缀匹配的字符串，如搜索引擎关键字检索。
![二叉树](http://www.xiangjw.tk/res/img/6.jpg "二叉树")
![二叉查找树](http://www.xiangjw.tk/res/img/7.jpg "二叉查找树")
   6. heap堆
      + CustomHeap实现了一个大顶堆，并封装其常用操作。常用于查找topN等（~~后续会实现其算法场景~~）。
![堆](http://www.xiangjw.tk/res/img/8.jpg "堆")
   7. graph图
      + CustomGraph基于数组加链表实现一个无向图，并实现了如下常用了算法场景。
         - 深度优先搜索；
         - 广度优先搜索。                                                               
![图](http://www.xiangjw.tk/res/img/9.jpg "图")
   8. hash散列表
      + CustomHashTable封装简单的hashmap，实现常用put、get、remove操作。含数组、链表、散列表、跳表的插入查找性能对比。                       
![散列表](http://www.xiangjw.tk/res/img/10.jpg "散列表")
3. 常用算法部分（algo包）
   1. O实现复杂度分析 
      + OTest常见基本场景的时间复杂度分析与对比。
   2. recu递归算法
      + RecuTest利用递归实现了如下几种常用的业务场景，并对递归和非递归方式进行性能对比
         - 场景一：走台阶，每次可以走一步或者两步，共有多少种走法；
         - 场景二：给定一个字符数组，打印出所有可能得排列组合。
   3. sort排序算法
      + SortTest实现了如下几种常用排序算法，及性能对比。
         - 冒泡排序；
         - 选择排序；
         - 插入排序；
         - 归并排序；
         - 快速排序；
         - 桶排序；
         - 计数排序；
         - 基数排序。
   4. search查找算法
      + SearchTest实现有序数组的二分查找，有以下业务场景。
         - 不考虑重复元素时的简单二分查找；
         - 考虑重复元素查找第一个值等于给定值的元素；
         - 考虑重复元素查找最后一个值等于给定值的元素；
         - 考虑重复元素查找第一个大于等于给定值的元素；
         - 考虑重复元素查找最后一个小于等于给定值的元素。
      + StringCompare字符串匹配（单模式串匹配），主要实现如下几种方式（中文名是我根据算法特点取的，不喜勿喷~）。
         - BF简单粗暴算法；
         - RK巧妙散列比对算法；
         - BM跳跃式对比算法（结合坏字符和好后缀）；
         - KMP跳跃式对比算法（结合坏字符和好前缀）。
   5. lru缓存算法
      + LruArray利用数组实现lru的简单缓存工具。
      + LruLinkedList利用链表实现lru的简单缓存工具。

---
END
