package com.algorithm.leetcode;

import com.sun.org.apache.xml.internal.security.Init;

/**
 * 206. 反转链表
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseList {

    /**
     * 根据数组生成一个ListNode 测试用
     * @param arr
     * @return
     */
    public static ListNode initListNode(int[] arr) {
        if(arr.length == 0) {
            return null;
        }
        ListNode first = new ListNode(arr[0]);
        ListNode retNode = first;
        for (int i = 1; i < arr.length; i++ ) {
            first.next = new ListNode(arr[i]);
            first = first.next;
        }
        return retNode;
    }

    public static void main(String[] args) {
        ListNode listNode = initListNode(new int[]{1,2, 3, 4, 5});
        System.out.println(listNode);
//        ListNode res = reverseList(l1);
        ListNode res = reverseBetween(listNode, 2, 4);
        System.out.println(res);

    }

    /**
     * 不改变ListNode的val 只改变指针方向
     *
     *     pre   cur    next
     *    null    1  ->  2  ->  3  ->  4  ->  5
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转  1 ≤ m ≤ n ≤ 链表长度
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     */
    /**
     *           m             n
     *   pre    cur    next
     *    1  ->  2  ->  3  ->  4  ->  5  ->  NULL
     *
     *    1  ->  4  ->  3  ->  2  ->  5  ->  NULL
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0); // 需要一个假头节点, 处理m=1的情况
        dummy.next = head;
        ListNode pre = dummy;
        // 找到m位置
        for ( int i = 1; i < m; i ++ ) {
            pre = pre.next;
        }
        head = pre.next;
        for ( int i = m; i < n; i ++ ) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }


}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
//        return "ListNode{" +
//                "val=" + val +
//                ", next=" + next +
//                '}';
        return val + " -> " + next;
    }
}