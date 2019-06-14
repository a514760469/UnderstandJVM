package com.algorithm.leetcode;

/**
 * 24. 两两交换链表中的节点
 */
public class SwapLinkedListNodes {

    public static void main(String[] args) {
        ListNode listNode = ReverseList.initListNode(new int[]{1,2,3,4});
        System.out.println(listNode);
        System.out.println(swapPairs(listNode));
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // swap 操作
        ListNode p = dummy;
        while (p.next != null && p.next.next != null) {
            ListNode node1 = p.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;
            p.next = node2;
            node2.next = node1;
            node1.next = next;

            p = node1; // node1 和 node2已经交换
        }

        return dummy.next;
    }

}
