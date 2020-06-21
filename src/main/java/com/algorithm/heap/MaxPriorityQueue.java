package com.algorithm.heap;

/**
 * 最大堆
 * @author zhanglifeng
 * @since 2020-06-05 13:56
 */
public class MaxPriorityQueue<Key extends Comparable<Key>> {

    private Key[] pq;

    private int count;

    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(int capacity) {
        // 索引0不用
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public Key max() {
        return pq[1];
    }

    /** 插入元素 e */
    public void insert(Key e) {
        pq[++count] = e;
        swim(count);
    }

    /** 删除并返回当前队列中最大元素 */
    public Key delMax() {
        Key max = pq[1];
//        swap(1, count);
        pq[1] = pq[count];
        pq[count] = null;
        count--;
        sink(1);
        return max;
    }

    /** 上浮第 k 个元素，以维护最大堆性质 */
    private void swim(int k) {
        while (k > 1 && less(parent(k), k)) {
            // 和父节点交换
            swap(k, parent(k));
            k = parent(k);
        }
    }

    /** 下沉第 k 个元素，以维护最大堆性质 */
    private void sink(int k) {
        // 判断当前节点有没有孩子，只需判断当前节点有没有左孩子即可
        while (left(k) <= count) {
            int older = left(k);// 假设左节点为较大的节点
            // 如果右节点存在比一下大小
            if (right(k) <= count && less(older, right(k))) {
                older = right(k);
            }
            // 如果k比俩孩子都大，不必下沉了
            if (!less(k, older)) {
                break;
            }
            // 下沉
            swap(k, older);
            k = older;
        }
    }

    /**
     * 交换i j 的位置
     */
    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * pq[i]是否比 pq[j]小
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * pq (0, count]
     * 父节点的索引, 小于1则没有父节点
     */
    int parent(int root) {
        return root / 2;
    }


    /**
     * 左孩子的索引，大于count 则没有左孩子
     */
    int left(int root) {
        return root * 2;
    }

    /**
     * 右孩子的索引
     */
    int right(int root) {
//        return root * 2 + 1;
        return left(root) + 1;
    }
}
