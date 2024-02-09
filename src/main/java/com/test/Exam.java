package com.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhanglifeng
 * @since 2023-12-21
 */
public class Exam {

    public static void main(String[] args) {

//        int[] a = {1,2,3,5,6,7,8,9};
//        int i = binarySearch(a, 8);
//        System.out.println("i = " + i);

        int[] arr = {1,1,2,3,9,0,2,0,9,0,1,2,0, 9,9,9, 0,0, 9, 9};
        List<Node> nodes = process(arr);

        nodes.forEach(System.out::println);
    }

    /**
     * 搜索
     */
    public static int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (r >= l) {
            int m = l + (r - l) / 2;
            if (arr[m] == target) {
                return m;
            }
            else if (arr[m] < target) {
                l = m + 1;
            }
            else if (arr[m] > target) {
                r = m - 1;
            }
        }
        return -1;
    }


    public static List<Node> process(int[] arr) {
        Set<Integer> contains = new HashSet<>();
        List<Node> nodeList = new ArrayList<>();
        for (int i : arr) {
            if (contains.contains(i)) {
                for (Node node : nodeList) {
                    if (node.key == i) {
                        node.count++;
                        break;
                    }
                }
            } else {
                Node node = new Node(i, 1);
                nodeList.add(node);
                contains.add(i);
            }
        }

        // for loop impl
        LinkedList<Node> sorted = new LinkedList<>();
        for (Node node : nodeList) {
            if (sorted.isEmpty()) {
                sorted.add(node);
                continue;
            }
            if (sorted.getLast().count >= node.count) {
                sorted.addLast(node);
            }
            else if (sorted.getFirst().count <= node.count) {
                sorted.addFirst(node);
            }
            else {
                int index = sorted.size();
                while (index > 1) {
                    Node cur = sorted.get(index - 1);
                    if (node.count <= cur.count) {
                        break;
                    }
                    index -= 1;
                }
                sorted.add(index, node);
            }
        }
//        List<Node> sorted = nodeList.stream()
//                .sorted((o1, o2) -> o2.count - o1.count)
//                .collect(Collectors.toList());
        return sorted;
    }

    public static class Node implements Comparator<Node> {

        public int key;
        public int count;

        public Node() {
        }

        public Node(int key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public String toString() {
            return "{ key = " + key +
                    ", 出现了 " + count +
                    " 次}";
        }

        @Override
        public int compare(Node o1, Node o2) {
            return o2.count - o1.count;
        }
    }
}
