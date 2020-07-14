package com.algorithm;

/**
 * @author zhanglifeng
 * @since 2020-06-29
 */
public class FindKth {


    public static void main(String[] args) {

        int kth = new FindKth().findKth(new int[]{123, 12, 54, 5, 1, 2, 21}, 5);
        System.out.println(kth);
    }

    /**
     * 寻找数组中第k小的奇数
     */
    public int findKth(int[] arr, int k) {
        comparableInteger result = new comparableInteger(k);
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 1) {
                if (result.count == k) { // 如果存满了, 判断当前奇数是否小于堆中最大的元素
                    if (arr[i] < result.data[1]) {
                        result.delMax();
                        result.add(arr[i]);
                    }
                } else {
                    result.add(arr[i]);
                }
            }
        }

        return result.count == k ? result.data[1] : 0;
    }

    static class comparableInteger {
        final int[] data;
        int count;
        
        comparableInteger(int capacity) {
            data = new int[capacity + 1];
        }
        public void add(int number) {
            data[++count] = number;
            swim(count);
        }
        /**
         * 上浮第k个元素
         * @param k
         */
        private void swim(int k) {
            while (k > 1 && data[parent(k)] < data[k]) {
                swap(k, parent(k));
                k = parent(k);
            }
        }
        /**
         * 删除最大元素
         */
        public void delMax() {
            data[1] = data[count];
            data[count] = 0;
            count--;
            sink(1);
        }
        /**
         * 下沉
         */
        private void sink(int k) {
            while (left(k) <= count) {
                int older = left(k);
                // 如果右节点存在比一下大小
                if (right(k) <= count && data[older] < data[right(k)]) {
                    older = right(k);
                }
                if (data[k] > data[older]) {
                    break;
                }
                swap(k, older);
                k = older;
            }
        }

        void swap(int i, int j) {
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
        /**
         * 父节点的索引, 小于1则没有父节点
         */
        int parent(int root) {
            return root / 2;
        }
        /**
         * 左孩子的索引
         */
        int left(int root) {
            return root * 2;
        }
        /**
         * 右孩子的索引
         */
        int right(int root) {
            return left(root) + 1;
        }
    }

}
