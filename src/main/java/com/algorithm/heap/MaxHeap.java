package com.algorithm.heap;

/**
 * 最大堆
 * 数组实现
 */
public class MaxHeap<Item extends Comparable> {

    // 存放数据
    /*
     * 0  1  2   3  4  5  6  7
     * -  51 41 30 28 16 22 13
     */
    private Item[] data;
    // 数据数量
    private int count;

    // 容量
    private int capacity;

    /**
     * @param capacity 容量
     */
    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        // 不能直接声明泛型数组，只能先声明再强制转换
        data = (Item[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    /**
     * 将arr[] 数组构造成堆
     * @param arr
     * @param n
     */
    @SuppressWarnings("unchecked")
    public MaxHeap(Item[] arr, int n) {
        data = (Item[]) new Comparable[n + 1];
        this.capacity = n;
        for (int i = 0; i < n; i ++ ) {
            data[i+1] = arr[i];
        }
        this.count = n;
        for (int i = count / 2; i >= 1; i-- ) {
            shiftDown(i);
        }
    }

    /**
     * 返回总数
     * @return
     */
    public int size() {
        return this.count;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 新增一个元素
     * @param item
     */
    public void insert(Item item) {
        assert count + 1 <= capacity;

        data[count + 1] = item;
        count++;
        shiftUp(count);
    }

    /**
     * 提取最大元素
     * @return
     */
    public Item extractMax() {
        assert count > 0;
        // 取出根节点元素, 因为根节点就是最大的
        Item ret = data[1];
        // 取出之后将末尾元素放到根的位置
        swap(1, count);
        // 总数 -1
        count--;
        // shiftDown 根位置元素 使堆结构继续保持
        shiftDown(1);
        return ret;
    }

    /**
     * 向下交换
     * 取出元素时
     * @param k
     */
    private void shiftDown(int k) {
        // 判断当前节点有没有孩子， 只需要判断有没有左孩子即可
        while ( 2 * k <= count ) {
            // 此轮循环中 data[k] 和 data[j] 交换位置
            int j = 2 * k; // j : 左孩子  j + 1 : 右孩子
            if( j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {  // 判断有右孩子且右孩子大于左孩子
                // j = j + 1;   j指向右孩子
                j += 1;
            }
            if( data[j].compareTo(data[k]) <= 0) {
                break;
            }
            swap(j, k);
            k = j;
        }

    }

    /**
     * 向上交换
     * 插入元素时
     * @param k
     */
    private void shiftUp(int k) {
        // k/2 为当前节点父节点
        // k = 1 为根节点
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 交换堆中索引为i和j的两个元素
     */
    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        System.out.println(maxHeap.size());
        for ( int i = 0; i < 15; i++ ) {
            maxHeap.insert((int) (Math.random() * i));
        }
        System.out.println(maxHeap.size());
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.extractMax() + " ");
        }
        System.out.println();
    }

}
