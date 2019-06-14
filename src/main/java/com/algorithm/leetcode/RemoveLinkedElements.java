package com.algorithm.leetcode;

/**
 * 删除链表元素
 */
public class RemoveLinkedElements {

    public static void main(String[] args) {
        ListNode listNode = ReverseList.initListNode(new int[]{1,1});
        System.out.println(listNode);
        System.out.println(removeElements(listNode, 1));
    }

    /**
     * 移出ListNode所有值为val的元素
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);// 假头结点 放在head之前, 处理head是val的情况
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;// cur不动, cur.next指向cur.next.next
            } else {
                cur = cur.next;// cur移动
            }
        }
        return dummy.next;
    }

}
