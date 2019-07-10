package com.algorithm.heap;

/**
 * 最大索引堆
 * 元素比较的时候比较的是data中的元素，交换的时候交换的是indexes中的元素
 */
public class IndexMapHeap<Item extends Comparable> {

    /**
     * 存放数据
     */
    private Item[] data;

    /**
     * 索引数组，代替data中的元素来进行交换
     */
    private int[] indexes;

    /**
     * 最大索引堆中的反向索引, reverse[i] = x 表示索引i在x的位置
     */
    private int[] reverse;

    /**
     * 数据数量
     */
    private int count;

    /**
     * 容量
     */
    private int capacity;

    /**
     * @param capacity 容量
     */
    @SuppressWarnings("unchecked")
    public IndexMapHeap(int capacity) {
        // 不能直接声明泛型数组，只能先声明再强制转换
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    /**
     * 将arr[] 数组构造成堆
     * @param arr
     * @param n
     */
    @SuppressWarnings("unchecked")
    public IndexMapHeap(Item[] arr, int n) {
        data = (Item[]) new Comparable[n + 1];
        indexes = new int[n + 1];
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
     * 插入一个元素
     * 传入的i对用户而言是从0索引的
     * @param item
     */
    public void insert(int i, Item item) {
        assert count + 1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;
        i += 1;

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
        Item ret = data[indexes[1]];
        // 取出之后将末尾元素放到根的位置
        swap(1, count);
        // 总数 -1
        count--;
        // shiftDown(1) 根位置元素 使堆结构继续保持
        shiftDown(1);
        return ret;
    }

    /**
     * 取出最大索引
     * @return
     */
    public int extractMaxIndex() {
        assert count > 0;
        int ret = indexes[1] - 1;
        // 取出之后将末尾元素放到根的位置
        swap(1, count);
        // 总数 -1
        count--;
        // shiftDown(1) 根位置元素 使堆结构继续保持
        shiftDown(1);
        return ret;
    }

    /**
     *
     * @param i
     * @return
     */
    Item getItem(int i) {
        return data[i+1];
    }

    void change(int i, Item newItem) {

        i += 1;
        data[i] = newItem;
        // 找到indexes[j] = i , j 表示data[i] 在堆中的位置
        // 之后shiftUp(j) 再shiftDown(j)
        for ( int j = 1; i <= count; j ++ ) {
            if (indexes[j] == i) {
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }

    }
    /**
     * 向下交换
     * 取出元素时
     *
     * @param k
     */
    private void shiftDown(int k) {
        // 判断当前节点有没有孩子， 只需要判断有没有左孩子即可
        while ( 2 * k <= count ) {
            // 此轮循环中 data[k] 和 data[j] 交换位置
            int j = 2 * k; // j : 左孩子  j + 1 : 右孩子
            if( j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {  // 判断有右孩子且右孩子大于左孩子
                // j = j + 1;   j指向右孩子
                j += 1;
            }
            if( data[indexes[j]].compareTo(data[indexes[k]]) <= 0 ) {
                // 进入这里表示k位置元素比最大的孩子还大，不需要做交换，直接退出循环
                break;
            }
            swap(j, k);
            // 此时k到了j的位置
            k = j;
        }

    }

    /**
     * 向上交换
     * 插入元素时
     *
     * 规律：插入位置为 第一个非叶子节点，第一个非叶子几点的位置为 count / 2
     * 比较新插入的值和其父的大小，如果比其父大就交换，继续维持二叉堆的结构
     * @param k
     */
    private void shiftUp(int k) {
        // k/2 为当前节点父节点
        // k = 1 为根节点
        while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) < 0) {
            // 交换的也是indexes
            swap(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 交换堆中索引为i和j的两个元素
     */
    private void swap(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }

}
