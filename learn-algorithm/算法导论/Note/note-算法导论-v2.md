# 算法导论
## 第1章-算法在计算中的作用
### 1.1 算法
- 算法是什么
  - 算法是一种求解特定问题的计算过程。
- 数据结构是什么
  - 数据结构是一种存储和组织数据的方式。
  - 此处提到一个NP问题
    - 概念
      - NP问题是：多项式复杂程度的非确定性问题
      - 注：暂时不理解，后续更新
- 算法导论的作用
  - 教学一些算法设计与分析的技术，以便读者能自行设计算法、证明其正确性和理解其效率。

### 1.1 作为一种技术的算法


## 第2章 算法基础
### 2.1 插入排序
- 输入：n个数的一个序列(a1,a2,a3,...,an)。
- 输出：输入序列的一个排列(a1`,a2`,a3`,...,an`)。
- 插入排序
  - 简介
    - 对于少量元素的排序，它是一个有效的算法。
  - 过程
    1. 取出i位置上的值，将其与1到i位置上的值逐一进行比较，直到找到其正确位置。
    2. i指向下一个位置，重复1步骤。(i从第一个位置直到最后一个位置)
```伪代码
for j = 2 to A.length
    key = A[j]
    // 将A[j] 插入到A[1,j-1]中
    i = j - 1
    while i > 0 and A[i] > key
        A[i + 1] = A[i]
        i = i - 1
    A[i + 1] = key
```

### 2.2 分析算法

### 2.3 设计算法

#### 2.3.1 分治法
- 递归
  - 为了解决一个给定的问题，算法一次或者多次递归地调用其自身以解决紧密相关的若干子问题。
- 分治
  - 将原问题分解为几个规模较小但类似于原问题的子问题，递归地求解这些子问题，然后再合并这些子问题的解来建立原问题。
  - 分支模式再每层递归时都有三个步骤
    1. 分解原问题为若干子问题，这些子问题时原问题的规模较小的实例。
    2. 解决这些子问题。递归地求解各子问题。然而，若子问题的规模足够小，则直接求解。
    3. 合并这些子问题的解成原问题的解。
- 分支法例子
  - 归并排序(归并排序是严格的分治法)
  - 归并排序的简介


``` 伪代码 MERGE(A,p,q,r)
// 计算出第一个串的归并数量
n1 = q - p + 1
// 计算出第二个串的归并数量
n2 = r - q
// 重新赋值给新的数组-具体实现时操作下表即可
let L[1...n1+1] and R[1...n2+1] be new arrays
for i = 1 to n1
  L[i] = A[p + i - 1]
for j = 1 to n2
  R[i] = A[q + j]
// 末尾置为无穷大-方便对比  
L[n1 + 1] = MAX
R[n2 + 1] = MAX
// 开始归并
i = 1
j = 1
for k = p to r
  if L[i] <= R[j]
    A[k] = L[i]
    i = i + 1
  else
    A[k] = R[j]
```

``` 伪代码 MERGE-SORT(A,p,r)
if(p < r)
  q = (p + r) / 2 [向下取整]
  // 排序前一半
  MERGE-SORT(A, p, q)
  // 排序后一半
  MERGE-SORT(A, q + 1, r)
  // 排序全部
  MERGE(A, p, q, r)
```
#### 2.3.2 分治法分析

## 第3章 函数的增长(！！！暂缺！！！)

## 第4章 分治策略
### 4.1 最大子数组问题
- 例
  - 设存在数组
    - {110,113,110,85,,105,102,86,63,81,101,94,106,101,79,94,90,97}
    - 其中n[x],n[y]均属于该数组，且y>x，求xy为何值时n[y] - n[x]取最大值
  - 解法
    1. 暴力求解
    2. 递归(分治策略)
    - 分治策略解法
    - 该分治策略就是从每个位置开始找寻往左右两边进行探测，看能达到的最大数是多少。也是一种遍历。空间换了时间。
```伪代码
FIND-MAX-CROSSING-SUBArrAY(A, low, mid, high)
  // 初始化左边数组和为最小值
  left-sum = MIN
  // 初始化sum，记录当前数组中的最大值
  sum = 0
  // 从mid位置开始向左边进行探测，获取当前最大和的数组边界
  for i = mid downto low
    sum = sum + A[i]
    if(sum > left-sum) {
      // 如果新的探测和大于原本的和，则更新和
      left-sum = sum
      // 且充值下表
      max-left
    } 
  // 右边同理
  right-sum = MIN
  sum = 0
  for j = mid + 1 to high
    sum = sum + A[i]
    if sum > right-sum
      right-sum = sum
      max-right = j
return (max-left, max-right, left-sum + right-sum)
```
```伪代码
FIND-MAX-SUBARRAY(A, low, high)
  if high == low
    return (low, high, A[low])
  else mid = (low + high) / 2 (向下取整)
    // 找左边最大子数组
    (left-low, left-high, left-sum) = FIND-MAX-SUBARRAY(A, low, mid)
    // 找右边最大子数组
    (right-low, right-high, right-sum) = FIND-MAX-SUBARRAY(A, mid + 1, high)
    // 找以某个位置为起点向左右两边探测出的最大子数组
    (cross-low, cross-high, cross-sum) = FIND-MAX-CROSSING-SUBARRAY(A, low, mid, high)
    // mid为起点向左边探测的和大于mid为起点向右边探测的和且大于mid为起点向左右探测的和
    if(left-sum >= right-sum and left-sum >= cross-sum)
      return (left-low, left-high, left-sum)
    // mid为起点向右边探测的和大于mid为起点向左边探测的和且大于mid为起点向左右探测的和
    elseif (right-sum >= left-sum and right-sum >= cross-sum)
      return (right-low, right-high, right-sum)
    // 否则为mid为起点向左右两边探测的值最大
    else
      return (cross-low, cross-hgih, cross-sum)
```

###  矩阵乘法的Strassen算法（暂缺）-原理就是分块矩阵乘法


## 第5章 概率分析和随机算法（暂缺）-概念之后再理解

## 第6章 堆排序
### 堆是什么
  - 堆（二叉堆）是一个以数组形式存储的完全二叉树，分为大根堆和小根堆。
  - 大根堆是指除了根以外的节点都满足：A[PARANT(i)] >= A[i] (即父节点的值大于等于其下子节点的值)。小根堆同理。
  - 该数组有两个属性，heap-size(有效元素数量)，length(当前数组元素个数)。注意：只有heap-size指定的才是当前堆的元素数量。
  - 一些必要的函数
```伪代码
// 获取当前元素的父元素
PARANT(i)
  return i/2 // 向下取整

// 获取当前元素的左孩子
LEFT(i)
  return 2i

// 获取当前元素的右孩子
RIGHT(i)
  return 2i + 1

```

### 维护堆的性质
  - 当堆中某元素不满足堆的定义时，需要对其进行维护，使得当前堆重新满足堆的性质
  - 使用其下函数
```伪代码
MAX-HEAPIFY(A,i) // i指当前的元素位置
  l = LEFT(i)
  r = RIGHT(i)
  if l <= A.heap-size and A[l] > A[i] // 左孩子大于当前节点
    largest = l
  else largest = i
  if r <= A.heap-size and A[r] > A[largest]
    largest = r
  if largest != i
    exchange A[i] with A[largest]
    MAX-HEAPIFY(A, largest)
```

### 建堆
  - 自底向上不断使用堆的性质维护函数来维护当前数据满足堆，最终形成一个堆。
```伪代码
BUILD-MAX-HEAP(A)
  A.heap-size = A.length
  for i = A.length / 2(向下取整) downto 1 // 从排在最后的一个父元素开始进行堆性质的维护
    MAX-HEAPIFY(A,i)
```

### 堆排序算法
  - 不断维护堆的性质生成最大(最小)堆，每次生成后取出最大或者最小值，剩下部分再次生成最大或者最小堆，直到排序完成
``` 伪代码
HEAPSORT(A)
  BUILD-MAX-HEAP(A)
  for i = A.length downto 2
    exchange A[1] with A[i]
    A.heap-size = A.heap-size - 1
    MAX-HEAPIFY(A, 1)
```

### 优先队列
  - 优先队列是一种用来维护由一组元素构成的集合S的数据结构，其中的每一个元素都有一个相关的值，称为关键字。一个最大优先队列支持以下操作：
    - INSERT(S, x): 把元素x插入集合S中。这一操作等价于 S=S并{x}
    - MAXIMUM(S): 返回S中具有最大关键字的元素
    - EXTRACT-MAX(S): 去掉并返回S中的具有最大关键字的元素
    - INCREASE-KEY(S, x, k): 将元素x的关键字值增加到k，这里假设k的值不小于x的原关键字。
  - 最大优先队列的应用举例
    - 共享计算机系统的作业调度
  - 关于实现
    - HEAP-MAXIMUM可以再O(1)时间内实现MAXIMUM操作
    - 过程 HEAP-EXTRACT-MAX 实现 EXTRACT-MAX 操作。它与 HEAPSORT 过程中的for循环体部分很相似
    - 过程 HEAP-INCREASE-KEY 能够实现 INCREASE-KEY 操作在优先队列中，我们希望增加关键字的优先队列元素对应的数组下标i来标识。这一操作需要首先将元素A[i]的关键字更新为新值。增大A[i]的关键字可能会违反最大堆的性质。
    - MAX-HEAP-INSERT 能够实现INSERT操作。它的输入是要被插入到最大堆A中的新元素的关键字。MAX-HEAP-INSERT首先通过增加一个关键字为负无穷的叶节点来扩展最大堆。然后调用 HEAP-INCREASE_KEY 为新结点设置对应的关键字，同时保持最大堆的性质
```伪代码

HEAP-MAXIMUM(A)
  return A[1]


HEAP-EXTRACT-MAX(A)
  if A.heap-size < 1
    error "heap underflow"
  max = A[1]
  A[1] = A[A.heap-size]
  A.heap-size = A.heap-size - 1
  MAX-HEAPIFY(A, 1)
  return max

HEAP-INCREASE-KEY(A, i, key)
  if key < A[i]
    error "new key is samller than current key"
  A[i] = key
  while i > 1 and A[PARENT(i)] < A[i]
    exchange A[i] with A[PARENT(i)]
    i = PARENT(i)


MAX-HEAP-INSERT(A, key)
  A.heap-size = A.heap-size + 1
  A[A.heap-size] = 负无穷
  HEAP-INCREASE-KEY(A, A.heap-size, key)

```


## 第7章 快速排序
### 7.1 快速排序的描述
- 与归并排序一样，快速排序也使用了分治的思想。
- 分组：数组A[p..r]被划分为两个(可能为空)子数组A[p..q-1]，使得A[p...q-1]中的每一个元素都小于等于A[q]，而A[q]也小于等于A[q+1...r]中的每个元素。

```伪代码
QUICKSORT(A,p,r)
  if(p < r)
    // 获取中位数应在在的下标
    q = PARTITION(A, p, r)
    QUICKSORT(A,p,q-1)
    QUICKSORT(A,q+1,r)

PARTITION(A, p, r)
  x = A[r]
  i = p - 1
  for j = p to r - 1
    if A[j] <= x
      i = i + 1
      exchange A[i] with A[j]
  exchange A[i + 1] with A[r]
  return i + 1
```

## 第12章 二叉搜索树
